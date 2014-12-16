/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.*;
import java.awt.Color;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import modelo.Horario;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import orthodent.db.AgendaDB;

public class AgendaSchedulerModel extends AbstractScheduleModel
{
    private String[] days = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
    private ArrayList<AgendaResource> resources;
    public ArrayList<Cita> citas;
    public BarraAcciones barra;

    @Override
    public void visitAppointments(AppointmentVisitor visitor, @NotNull LocalDate date)
    {
        for (Appointment cita : citas) {
            LocalDate appointmentDate = cita.getDateTime().toLocalDate();
            if (! appointmentDate.equals(date)) {
                continue;
            }
            visitor.visitAppointment(cita);
        }
    }

    public void visitResources(ResourceVisitor resourceVisitor, LocalDate localDate)
    {
        for(AgendaResource r : resources) {
            resourceVisitor.visitResource(r);
        }
    }

    public LocalTime getStartTime(LocalDate localDate)
    {
        return new LocalTime(9,0,0);
    }

    public LocalTime getEndTime(LocalDate localDate)
    {
        return new LocalTime(20,0,0);
    }
    
    public void agregarCita(Cita cita)
    {
        System.out.println("Cita agregada:" + ((AgendaResource)cita.getResource()).getPos());
        citas.add(cita);
        this.fireAppointmentAdded(cita);
    }
    
    public void eliminarCita(Cita cita){
        citas.remove(cita);
        this.fireAppointmentRemoved(cita);
    }
    
    public void actualizarCita(Cita cita){
        this.fireAppointmentUpdated(cita);
    }
    
    public AgendaSchedulerModel(BarraAcciones barra, boolean tieneProfesionales) 
    {
        
        this.citas = new ArrayList<Cita>();
        this.resources = new ArrayList<AgendaResource>();
        this.barra = barra;
        if(tieneProfesionales){
            int i = 0;
            for(String day : days) {
                AgendaResource r = new AgendaResource(day, i++, barra.getIdProfesional());
                this.resources.add(r);

            }
        }
        else{
            int i = 0;
            for(String day : days) {
                AgendaResource r = new AgendaResource(day, i++, -1);
                this.resources.add(r);
            }
        }
    }
    
    public AgendaResource calcularResource(Date fecha){
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int dia = c.get(Calendar.DAY_OF_WEEK);
        return resources.get(dia-2);
    }
    
    public void agregaCitaAlArray(Cita cita){
        this.citas.add(cita);
    }
    
    public Date obtenerLunes(Date fecha){
        Date lunes;
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        int semana = obtenerSemana(fecha);
        c.set(Calendar.WEEK_OF_YEAR, semana);
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        lunes = c.getTime();
        return lunes;
    }
    
    public int obtenerSemana(Date fecha){
        int semana=0;
        Date lunes;
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        semana = c.get(Calendar.WEEK_OF_YEAR);
        return semana;
    }
    
}

class AgendaResource implements Resource
{
    private final String _title;
    private int pos;
    private int id_profesional;

    public AgendaResource(String title, int pos, int id_prof)
    {
        _title = title;
        this.pos = pos;
        this.id_profesional = id_prof;
    }

    public Iterator<Availability> getAvailability(LocalDate localDate)
    {
        List<Availability> availabilities = new ArrayList<Availability>();
        ArrayList<Horario> horarios = AgendaDB.obtenerHorario(this._title, this.id_profesional);
        if(horarios==null || horarios.size()==0){   
            availabilities.add(new Availability(new LocalTime(0,0,0), Duration.standardMinutes(1)));            
        }
        else{
            int horaI, horaF, minutoI, minutoF, minutosT;
            for(Horario h : horarios ){
                horaI = h.getHora_inicio()/60;
                minutoI = h.getHora_inicio()%60;
                horaF = h.getHora_fin()/60;
                minutoF = h.getHora_fin()%60;
                minutosT = h.getHora_fin() - h.getHora_inicio();
                
                if(horaF>13){
                    int d1, d2;
                    d1 = (13*60)-h.getHora_inicio();
                    d2 = h.getHora_fin() - (15*60);
                    availabilities.add(new Availability(new LocalTime(horaI,minutoI,0), Duration.standardMinutes(d1)));
                    if(d2>0){
                        availabilities.add(new Availability(new LocalTime(15,0,0), Duration.standardMinutes(d2)));
                    }
                }
                else{                
                    availabilities.add(new Availability(new LocalTime(horaI,minutoI,0), Duration.standardMinutes(minutosT)));
                }
            }
        }
        return availabilities.iterator();
    }

    public String getTitle()
    {
        return _title;
        
    }
    
    public int getPos()
    {
        return pos;
    }
    
    
}