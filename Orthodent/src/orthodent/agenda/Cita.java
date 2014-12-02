package orthodent.agenda;

import com.thirdnf.ResourceScheduler.Appointment;
import com.thirdnf.ResourceScheduler.Resource;
import com.thirdnf.ResourceScheduler.components.BasicAppointmentComponent;
import java.awt.Color;
import java.awt.Graphics;
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
    private Resource _resource;
    private DateTime _realDateTime;
    private int profesionalId;
    private int pacienteId;
    private String fecha;
    private int semana;
    private int id;
    private boolean confirmada;
    private Color color;

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

    public DateTime getRealDateTime()
    {
        return _realDateTime;
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

    public void setRealDateTime(@NotNull DateTime time)
    {
        _realDateTime = time;
    }
    
    public static Cita create(@NotNull String title,
                                            @NotNull DateTime date, int minutes)
    {
        Cita appointment = new Cita(title);
        appointment.setDateTime(date);
        
        appointment.setDuration(Duration.standardMinutes(minutes));

        return appointment;
    }

    public void setResource(Resource r) {
       _resource = r;
    }
    
    @Override
    public Resource getResource() {
       return _resource;
    }

    public int getProfesionalId() {
        return profesionalId;
    }

    public void setProfesionalId(int profesionalId) {
        this.profesionalId = profesionalId;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSemana() {
        return semana;
    }

    public void setSemana(int semana) {
        this.semana = semana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isConfirmada() {
        return confirmada;
    }

    public void setConfirmada(boolean confirmada) {
        if(confirmada)
            this.color = new Color(127,255,81);
        else
            this.color = Color.YELLOW;
        this.confirmada = confirmada;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    
}
