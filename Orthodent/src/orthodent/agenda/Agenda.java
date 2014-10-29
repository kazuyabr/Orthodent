/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import javax.swing.*;
import java.awt.*;

import com.thirdnf.ResourceScheduler.Scheduler;
import org.joda.time.LocalDate;

/**
 *
 * @author Mary
 */
public class Agenda extends JPanel{
    
    public Agenda(){
        //Introducir código aquí
        this.setBackground(new Color(255,255,255));
        this.setPreferredSize(new Dimension(1073, 561));
        
        setSize(new Dimension(600, 600));

        Scheduler scheduler = new Scheduler();
        scheduler.setModel(new AgendaSchedulerModel());
        scheduler.showDate(new LocalDate());
        
        this.setLayout(new BorderLayout());
        add(scheduler, BorderLayout.CENTER);
    }
    
}
