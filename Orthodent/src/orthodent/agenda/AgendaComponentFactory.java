/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Appointment;
import com.thirdnf.ResourceScheduler.components.AbstractAppointmentComponent;
import com.thirdnf.ResourceScheduler.components.BasicComponentFactory;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Lucy
 */
public class AgendaComponentFactory  extends BasicComponentFactory{
    
    @Override
    public AbstractAppointmentComponent makeAppointmentComponent(@NotNull Appointment appointment){
        CitaComponent component = new CitaComponent(appointment);
        return component;
    }
}
