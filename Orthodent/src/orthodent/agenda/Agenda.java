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
    
    public Agenda(Usuario actual){
        this.usuarioActual = actual;
        this.modelo = new AgendaSchedulerModel();
        //Introducir código aquí
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(1073, 561));
        
        setSize(new Dimension(600, 600));

        this.scheduler = new Scheduler();
        this.scheduler.setModel(modelo);
        this.scheduler.showDate(new LocalDate());
        
        this.scheduler.setFont(new Font("Georgia", Font.PLAIN,12));

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
        new NuevaCita(((JFrame)this.getTopLevelAncestor()), true, resource, dateTime) {

            void agregarCita(Cita cita) {
                cita.setProfesionalId(barraAcciones.getIdProfesional());
                cita.setFecha(cita.getRealDateTime().toString("y-M-d"));
                cita.setSemana(obtenerSemana(cita.getRealDateTime().toDate()));
                
                modelo.agregarCita(cita);
                if(!AgendaDB.crearCita(cita,modelo)){
                    System.out.println("NO CREO LA WEA");
                }
            }
        
        }.setVisible(true);
    }
    
    public void cambiarSemanaDeAgenda(Date fecha){
        int semana = this.obtenerSemana(fecha);
        LocalDate ld = new LocalDate(obtenerLunes(fecha));
        //this.scheduler.showDate(ld);
        ArrayList<Cita> citas = AgendaDB.obtenerCitas(semana, barraAcciones.getIdProfesional(),modelo);
        if(citas!=null){
            for(Cita c : citas){
                System.out.println(c.getTitle());
                modelo.agregarCita(c);
                modelo.agregaCitaAlArray(c);
            }
        }
        else{
            System.out.println("Retorno NULO");
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
