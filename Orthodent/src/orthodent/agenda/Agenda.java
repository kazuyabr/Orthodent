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
import modelo.Usuario;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;

/**
 *
 * @author Mary
 */
public class Agenda extends JPanel{
    private Usuario usuarioActual;
        
    
    private BarraAcciones barraAcciones;
    
    public Agenda(Usuario actual){
        this.usuarioActual = actual;
        //Introducir código aquí
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(1073, 561));
        
        setSize(new Dimension(600, 600));

        Scheduler scheduler = new Scheduler();
        scheduler.setModel(new AgendaSchedulerModel());
        scheduler.showDate(new LocalDate());

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
        
        this.barraAcciones = new BarraAcciones(this.usuarioActual);
        
        this.add(barraAcciones, BorderLayout.NORTH);
        
    }
    
    private void handleAddAppointment(@Nullable Resource resource, @NotNull DateTime dateTime) {
        System.out.println(dateTime);
        new NuevaCita(((JFrame)this.getTopLevelAncestor()), true, dateTime).setVisible(true);
    }
}
