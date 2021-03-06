/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Appointment;
import com.thirdnf.ResourceScheduler.components.BasicAppointmentComponent;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucy
 */
public class CitaComponent extends BasicAppointmentComponent {

    private Cita cita;
    private int alto;
    private int ancho;
    private boolean agrandao;
    
    public CitaComponent(final Appointment a) {
        super(a);
        this.cita = (Cita) a;
        this.setFont(new Font("Georgia", Font.BOLD,9));
        this.alto = (int) Math.floor(cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes()*0.79);
        this.ancho = 161;
        this.agrandao = false;
        //this.setSize(ancho, alto);
        //this.getSize().width;
        //this.setToolTipText(a.getTitle());
        this.addMouseListener(new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent me) {
                cita.getAg().handleAddAppointment(a.getResource(), a.getDateTime().plusMillis(100));
            }

            @Override
            public void mousePressed(MouseEvent me) {
                
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                if(cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes()<=20){
                    setSize(ancho,20);
                    agrandao=true;
                    updateUI();
                }
            }

            @Override
            public void mouseExited(MouseEvent me) {
                if(agrandao){
                    setSize(ancho,alto);
                    updateUI();
                    agrandao=false;
                }
            }
            
        });
        
    }
    
    @Override
    public void paintComponent(Graphics g){
        setBackground(this.cita.getColor());
        this.setAlignmentY(TOP_ALIGNMENT);
        
        //int altura = this.cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes();
        //this.setSize(161, 11);
        super.paintComponent(g);
    }
    
    
    
}
