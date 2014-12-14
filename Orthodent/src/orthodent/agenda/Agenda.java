/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Availability;
import com.thirdnf.ResourceScheduler.DaySchedule;
import com.thirdnf.ResourceScheduler.Resource;
import com.thirdnf.ResourceScheduler.ScheduleListener;
import javax.swing.*;
import java.awt.*;

import com.thirdnf.ResourceScheduler.Scheduler;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import modelo.Usuario;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import orthodent.JVentana;
import orthodent.db.AgendaDB;

/**
 *
 * @author Mary
 */
public class Agenda extends JPanel{
    private Usuario usuarioActual;
        
    private BarraAcciones barraAcciones;
    private AgendaSchedulerModel modelo;
    public Scheduler scheduler;
    private HashMap<Integer,Boolean> semanasCargadas;
    private HashMap<Integer,ArrayList<Cita>> citasDeLaSemana;
    private int semanaActual;
    
    public Agenda(Usuario actual){
        this.usuarioActual = actual;
        this.barraAcciones = new BarraAcciones(this.usuarioActual,this);
        //this.cambiarSemanaDeAgenda(new Date());
        
        this.modelo = new AgendaSchedulerModel(this.barraAcciones);
        //Introducir código aquí
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(1073, 561));
        this.semanasCargadas = new HashMap<Integer,Boolean>();
        this.citasDeLaSemana = new HashMap<Integer,ArrayList<Cita>>();
        setSize(new Dimension(600, 600));

        this.scheduler = new Scheduler();
        this.scheduler.setModel(modelo);
        this.scheduler.showDate(new LocalDate());
        this.scheduler.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.scheduler.setBackground(Color.WHITE);
        scheduler.addScheduleListener(new ScheduleListener()
        {
            @Override
            public void actionPerformed(@NotNull Resource resource, @NotNull DateTime time)
            {
                handleAddAppointment(resource, time);
            }
        });
        this.setLayout(new BorderLayout());
        AgendaComponentFactory cf = new AgendaComponentFactory();
        this.scheduler.setComponentFactory(cf);
        add(this.scheduler, BorderLayout.CENTER);
        this.barraAcciones.setFechaAgenda(new Date());
        this.add(barraAcciones, BorderLayout.NORTH);
        ((DaySchedule)(this.scheduler.getComponent(0))).setBackground(new Color(11,146,181));
        updateUI();
    }
    
    public void handleAddAppointment(@Nullable Resource resource, @NotNull DateTime dateTime) {
        
        if(this.usuarioActual.getId_rol()==3 || this.usuarioActual.getId_rol()==4) return;
        boolean clickEnHorarioDeAtencion = false;
        Iterator<Availability> av = resource.getAvailability(dateTime.toLocalDate());
        while(av.hasNext()){
            Availability a = av.next();
            DateTime inicioBloque = new DateTime(dateTime.getYear(),dateTime.getMonthOfYear(),dateTime.getDayOfMonth(),a.getTime().getHourOfDay(),a.getTime().getMinuteOfHour(),0,0);
            DateTime finBloque = inicioBloque.plusMinutes(a.getDuration().toStandardSeconds().toStandardMinutes().getMinutes());
            if(dateTime.isAfter(inicioBloque) && dateTime.isBefore(finBloque)){
                clickEnHorarioDeAtencion = true;
            }
        }
        
        if(!clickEnHorarioDeAtencion) return;
        
        
        int semana = obtenerSemana(dateTime.toDate());
        Cita clickeada = null;
        boolean esta = false;
        
        if(citasDeLaSemana.containsKey(semana)){
            for(Cita c : citasDeLaSemana.get(semana)){
                if(c.getResource()==resource && (dateTime.getMinuteOfDay()>=c.getDateTime().getMinuteOfDay() && dateTime.getMinuteOfDay()<=(c.getDateTime().plusMinutes(c.getDuration().toStandardSeconds().toStandardMinutes().getMinutes())).getMinuteOfDay())){
                    esta = true;
                    clickeada = c;
                    break;
                }
            }
        }
        
        
        if(!esta){
            new NuevaCita(((JFrame)this.getTopLevelAncestor()), true, resource, dateTime) {

                Boolean agregarCita(Cita cita) {
                    cita.setProfesionalId(barraAcciones.getIdProfesional());
                    cita.setFecha(cita.getRealDateTime().toString("y-M-d"));
                    cita.setSemana(obtenerSemana(cita.getRealDateTime().toDate()));
                    cita.setConfirmada(false);
                    cita.setAg(((JVentana)super.getParent()).getAgenda());
                    if(validarBloques(cita) && validarTopeHora(cita)){
                        modelo.agregarCita(cita);
                        if(!citasDeLaSemana.containsKey(cita.getSemana())){
                            ArrayList<Cita> citas = new ArrayList<Cita>();
                            citas.add(cita);
                            citasDeLaSemana.put(cita.getSemana(), citas);
                        }
                        else{
                            citasDeLaSemana.get(cita.getSemana()).add(cita);
                        }
                        if(!AgendaDB.crearCita(cita,modelo)){
                            
                            return false;
                        }
                        return true;
                    }
                    return false;
                }


            }.setVisible(true);
        }
        else{
            
            new EditarCita(((JFrame)this.getTopLevelAncestor()), true, clickeada, dateTime, resource){

                @Override
                Boolean actualizarCita(Cita cita, Cita citaVieja) {
                    cita.setProfesionalId(barraAcciones.getIdProfesional());
                    cita.setFecha(cita.getRealDateTime().toString("y-M-d"));
                    cita.setSemana(obtenerSemana(cita.getRealDateTime().toDate()));
                    cita.setAg(((JVentana)super.getParent()).getAgenda());
                    if(validarBloques(cita) && validarTopeHora(cita)){
                        if(AgendaDB.actualizarCita(cita)){
                            citasDeLaSemana.get(cita.getSemana()).remove(citaVieja);
                            citasDeLaSemana.get(cita.getSemana()).add(cita);
                            modelo.eliminarCita(citaVieja);
                            modelo.agregarCita(cita);
                            updateUI();
                            return true;
                        }
                        return false;
                    }
                    if(!validarBloques(cita)) System.out.println("ValidarBloques");
                    if(!validarTopeHora(cita)) System.out.println("ValidarTopeHora");
                    return false;
                }

                @Override
                void eliminarCita(Cita cita) {
                    
                    if(AgendaDB.eliminarCita(cita)){
                        citasDeLaSemana.get(obtenerSemana(cita.getRealDateTime().toDate())).remove(cita);
                        modelo.eliminarCita(cita);
                    }
                           
                }
                
            }.setVisible(true);
            
        }
    }
    
    public void cargarAgendainicial(){
        int semana = this.obtenerSemana(new Date());
        this.semanaActual = semana;
        ArrayList<Cita> citas = AgendaDB.obtenerCitas(semana, barraAcciones.getIdProfesional(),modelo);
        if(citas!=null){
            this.semanasCargadas.put(semana, Boolean.TRUE);
            this.citasDeLaSemana.put(semana, citas);
            for(Cita c: citas){
                modelo.agregarCita(c);
                c.setAg(this);
            }
            updateUI();
        }
    }
    
    
    public void cambiarSemanaDeAgenda(Date fecha){
        int semana = this.obtenerSemana(fecha);
        this.semanaActual = semana;
        LocalDate ld = new LocalDate(obtenerLunes(fecha));
        this.scheduler.showDate(ld);
        if(!this.semanasCargadas.containsKey(semana)){
            ArrayList<Cita> citas = AgendaDB.obtenerCitas(semana, barraAcciones.getIdProfesional(),modelo);
            if(citas!=null){
                this.citasDeLaSemana.put(semana, citas);
                this.semanasCargadas.put(semana, Boolean.TRUE);
                for(Cita c : citas){
                    System.out.println(c.getTitle());
                    modelo.agregarCita(c);
                    c.setAg(this);
                }
            }
            else{
                System.out.println("Retorno NULO");
            }
        }
        
    }
    
    public void cambiarProfesional(Date fecha){
        int semana = this.obtenerSemana(fecha);
        this.semanaActual = semana;
        LocalDate ld = new LocalDate(obtenerLunes(fecha));
        this.scheduler.showDate(ld);
        this.modelo = new AgendaSchedulerModel(this.barraAcciones);
        this.scheduler.setModel(modelo);
        this.citasDeLaSemana.clear();
        this.semanasCargadas.clear();
        this.modelo.citas.clear();
        if(!this.semanasCargadas.containsKey(semana)){
            ArrayList<Cita> citas = AgendaDB.obtenerCitas(semana, barraAcciones.getIdProfesional(),modelo);
            if(citas!=null){
                for(Cita c : citas){
                    System.out.println(c.getTitle());
                    modelo.agregarCita(c);
                    this.semanasCargadas.put(semana, Boolean.TRUE);
                    this.citasDeLaSemana.put(semana, citas);
                    c.setAg(this);
                }
            }
            else{
                System.out.println("Retorno NULO");
            }
        }
    }
    
    public void avanzarSemana(){
        Date fecha = this.barraAcciones.getFechaAgenda();
        Date fechaNueva = this.addDaysToDate(fecha, 7);
        this.barraAcciones.setFechaAgenda(fechaNueva);
        //this.cambiarSemanaDeAgenda(fechaNueva);
    }
    
    public void retrocederSemana(){
        Date fecha = this.barraAcciones.getFechaAgenda();
        Date fechaNueva = this.addDaysToDate(fecha, -7);
        this.barraAcciones.setFechaAgenda(fechaNueva);
        //this.cambiarSemanaDeAgenda(fechaNueva);
    }
    
    public Date addDaysToDate(Date date, int noOfDays) {
        Date newDate = new Date(date.getTime());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, noOfDays);
        newDate.setTime(calendar.getTime().getTime());

        return newDate;
}
    
    public int obtenerSemana(Date fecha){
        int semana=0;
        Date lunes;
        Calendar c = Calendar.getInstance();
        c.setTime(fecha);
        semana = c.get(Calendar.WEEK_OF_YEAR);
        return semana;
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
    
    public Boolean validarTopeHora(Cita cita){
        boolean sePuede = true;
        int semana = this.obtenerSemana(cita.getRealDateTime().toDate());
        DateTime horaInicio = cita.getDateTime();
        DateTime horaFin = cita.getDateTime().plusMinutes(cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes());
        System.out.println(horaFin.toString());
        
        if(this.citasDeLaSemana.containsKey(semana)){
            for(Cita c : this.citasDeLaSemana.get(semana)){
                if(c.getId()!=cita.getId()){
                    DateTime citaFin = c.getDateTime().plusMinutes(c.getDuration().toStandardSeconds().toStandardMinutes().getMinutes());
                    DateTime citaInicio = c.getDateTime();
                    if(c.getResource()==cita.getResource()){
                        if(horaFin.isBefore(citaFin) && horaFin.isAfter(citaInicio)){
                            sePuede = false;
                        }
                        else if((horaInicio.isAfter(citaInicio) || horaInicio.isEqual(citaInicio)) && horaInicio.isBefore(citaFin)){
                            sePuede = false;
                        }
                        else if(horaInicio.isBefore(citaInicio) && horaFin.isAfter(citaFin)){
                            sePuede = false;
                        }
                    }
                }
            }
        }
        
        return sePuede;
    }
    
    public Boolean validarBloques(Cita cita){
        boolean sePuede = false;
        Iterator<Availability> av = cita.getResource().getAvailability(cita.getRealDateTime().toLocalDate());
        while(av.hasNext()){
            Availability a = av.next();
            DateTime inicioBloque = new DateTime(cita.getDateTime().getYear(),cita.getDateTime().getMonthOfYear(),cita.getDateTime().getDayOfMonth(),a.getTime().getHourOfDay(),a.getTime().getMinuteOfHour(),0,0);
            DateTime finBloque = inicioBloque.plusMinutes(a.getDuration().toStandardSeconds().toStandardMinutes().getMinutes());
            DateTime finCita = cita.getDateTime().plusMinutes(cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes());
            if(cita.getDateTime().isAfter(inicioBloque) && finCita.isBefore(finBloque)){
                sePuede = true;
            }
        }
        return sePuede;
    }
    
    public void printComponenet(){

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");

        pj.setPrintable (new Printable() {    
            @Override
            public int print(Graphics pg, PageFormat pf, int pageNum){
                if (pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.scale(0.7,0.7);
                g2.translate(pf.getImageableX(), pf.getImageableY());
                scheduler.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false)
            return;

        try {
            pj.print();
        }
        catch (PrinterException ex) {
                // handle exception
        }
    }
    
}
