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

public class AgendaSchedulerModel extends AbstractScheduleModel
{
    private String[] days = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

    public void visitAppointments(AppointmentVisitor appointmentVisitor, LocalDate localDate)
    {
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
        return (new ArrayList<Availability>()).iterator();
    }

    public String getTitle()
    {
        return _title;
    }
}