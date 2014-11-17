/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.agenda;

import com.thirdnf.ResourceScheduler.*;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.joda.time.Duration;

public class AgendaSchedulerModel extends AbstractScheduleModel
{
    private String[] days = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};
    private ArrayList<Cita> citas;

    @Override
    public void visitAppointments(AppointmentVisitor visitor, @NotNull LocalDate date)
    {
        for (Cita cita : citas) {
            LocalDate appointmentDate = cita.getDateTime().toLocalDate();
            if (! appointmentDate.equals(date)) {
                continue;
            }
            visitor.visitAppointment(cita);
        }
    }

    public void visitResources(ResourceVisitor resourceVisitor, LocalDate localDate)
    {
        for(String day : days) {
            Resource r = new AgendaResource(day);
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
        citas.add(cita);
        this.fireAppointmentAdded(cita);
    }
    
    public AgendaSchedulerModel() 
    {
        this.citas = new ArrayList<Cita>();
    }
}

class AgendaResource implements Resource
{
    private final String _title;

    public AgendaResource(String title)
    {
        _title = title;
    }

    public Iterator<Availability> getAvailability(LocalDate localDate)
    {
       List<Availability> availabilities = new ArrayList<Availability>();
       availabilities.add(new Availability(new LocalTime(9,0,0), Duration.standardHours(11)));
       return availabilities.iterator();
    }

    public String getTitle()
    {
        return _title;
        
    }
}