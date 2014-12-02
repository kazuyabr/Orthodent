/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Appointment;
import com.thirdnf.ResourceScheduler.components.BasicAppointmentComponent;
import java.awt.Graphics;

/**
 *
 * @author Lucy
 */
public class CitaComponent extends BasicAppointmentComponent {

    private Cita cita;
    
    public CitaComponent(Appointment a) {
        super(a);
        this.cita = (Cita) a;
    }
    
    @Override
    public void paintComponent(Graphics g){
        setBackground(this.cita.getColor());
        super.paintComponent(g);
    }
    
    
    
}
