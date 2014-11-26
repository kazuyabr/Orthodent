/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Resource;
import com.thirdnf.ResourceScheduler.ScheduleListener;
import javax.swing.*;
import java.awt.*;

import com.thirdnf.ResourceScheduler.Scheduler;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import modelo.Usuario;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
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
    
    public Agenda(Usuario actual){
        this.usuarioActual = actual;
        this.modelo = new AgendaSchedulerModel();
        //Introducir código aquí
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(1073, 561));
        this.semanasCargadas = new HashMap<Integer,Boolean>();
        this.citasDeLaSemana = new HashMap<Integer,ArrayList<Cita>>();
        setSize(new Dimension(600, 600));

        this.scheduler = new Scheduler();
        this.scheduler.setModel(modelo);
        this.scheduler.showDate(new LocalDate());

        scheduler.addScheduleListener(new ScheduleListener()
        {
            @Override
            public void actionPerformed(@NotNull Resource resource, @NotNull DateTime time)
            {
                handleAddAppointment(resource, time);
            }
        });
        this.setLayout(new BorderLayout());
        add(scheduler, BorderLayout.CENTER);
        
        this.barraAcciones = new BarraAcciones(this.usuarioActual,this);
        //this.cambiarSemanaDeAgenda(new Date());
        this.barraAcciones.setFechaAgenda(new Date());
        this.add(barraAcciones, BorderLayout.NORTH);
    }
    
    private void handleAddAppointment(@Nullable Resource resource, @NotNull DateTime dateTime) {
        System.out.println(dateTime);
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

                void agregarCita(Cita cita) {
                    cita.setProfesionalId(barraAcciones.getIdProfesional());
                    cita.setFecha(cita.getRealDateTime().toString("y-M-d"));
                    cita.setSemana(obtenerSemana(cita.getRealDateTime().toDate()));

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
                        System.out.println("NO CREO LA WEA");
                    }
                }

            }.setVisible(true);
        }
        else{
            System.out.println("CLICKEO UNA QUE YA ESTAAAA!!!");
            new EditarCita(((JFrame)this.getTopLevelAncestor()), true, clickeada, dateTime, resource){

                @Override
                void actualizarCita(Cita citaNueva, Cita citaAntigua) {
                    citaNueva.setProfesionalId(barraAcciones.getIdProfesional());
                    citaNueva.setFecha(cita.getRealDateTime().toString("y-M-d"));
                    citaNueva.setSemana(obtenerSemana(cita.getRealDateTime().toDate()));
                    
                    if(AgendaDB.actualizarCita(citaNueva)){
                        citasDeLaSemana.get(obtenerSemana(cita.getRealDateTime().toDate())).remove(citaAntigua);
                        citasDeLaSemana.get(obtenerSemana(cita.getRealDateTime().toDate())).add(citaNueva);
                        modelo.eliminarCita(citaAntigua);
                        modelo.agregarCita(citaNueva);
                    }
                    
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
    
    public void cambiarSemanaDeAgenda(Date fecha){
        int semana = this.obtenerSemana(fecha);
        LocalDate ld = new LocalDate(obtenerLunes(fecha));
        this.scheduler.showDate(ld);
        if(!this.semanasCargadas.containsKey(semana)){
            ArrayList<Cita> citas = AgendaDB.obtenerCitas(semana, barraAcciones.getIdProfesional(),modelo);
            if(citas!=null){
                for(Cita c : citas){
                    System.out.println(c.getTitle());
                    modelo.agregarCita(c);
                    this.semanasCargadas.put(semana, Boolean.TRUE);
                    this.citasDeLaSemana.put(semana, citas);
                }
            }
            else{
                System.out.println("Retorno NULO");
            }
        }
        
    }
    
    public void cambiarProfesional(Date fecha){
        int semana = this.obtenerSemana(fecha);
        LocalDate ld = new LocalDate(obtenerLunes(fecha));
        this.scheduler.showDate(ld);
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
                }
            }
            else{
                System.out.println("Retorno NULO");
            }
        }
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
    
    
    
}
