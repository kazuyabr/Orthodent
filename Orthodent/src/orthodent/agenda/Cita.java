package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Appointment;
import com.thirdnf.ResourceScheduler.Resource;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;

/**
 * Example Appointment
 *
 * @author Joshua Gerth - jgerth@thirdnf.com
 */
public class Cita implements Appointment
{
    private String _title;
    private DateTime _dateTime;
    private Duration _duration;


    public Cita(@NotNull String title)
    {
        _title = title;
    }

    /**
     *
     * @return
     */
    @Override
    public DateTime getDateTime()
    {
        return _dateTime;
    }


    @NotNull
    public Duration getDuration()
    {
        return _duration;
    }


    public void setDuration(@NotNull Duration duration)
    {
        _duration = duration;
    }


    @NotNull
    public String getTitle()
    {
        return _title;
    }


    /**
     * Set the title of the appointment.
     *
     * @param title (not null) New title for the appointment
     */
    public void setTitle(@NotNull String title)
    {
        _title = title;
    }


    public void setDateTime(@NotNull DateTime time)
    {
        _dateTime = time;
    }


    public static Cita create(@NotNull String title,
                                            @NotNull DateTime date, int minutes)
    {
        Cita appointment = new Cita(title);
        appointment.setDateTime(date);
        appointment.setDuration(Duration.standardMinutes(minutes));

        return appointment;
    }

    @Override
    public Resource getResource() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
