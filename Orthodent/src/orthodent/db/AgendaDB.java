/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Paciente;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import orthodent.agenda.AgendaSchedulerModel;
import orthodent.agenda.Cita;

/**
 *
 * @author Lucy
 */
public class AgendaDB {
    
    public static boolean crearCita(Cita cita, AgendaSchedulerModel modelo){
        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            
            int id_profesional = cita.getProfesionalId();
            int id_paciente = cita.getPacienteId();
            String fecha = cita.getFecha();
            String horaInicio = cita.getRealDateTime().toString("h:m");
            int duracion = cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes(); 
            int semana = cita.getSemana();
            System.out.println("hora:"+horaInicio+"     fecha:"+fecha);
            int aux = st.executeUpdate("INSERT INTO cita (id_profesional, id_paciente, fecha, hora_inicio, duracion, semana, comentario)\n"+
                    "VALUES ("+id_profesional+","+id_paciente+",'"+fecha+"','"+horaInicio+"',"+duracion+","+semana+",'Sin Comentario')");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        
    }
    
    public static ArrayList<Cita> obtenerCitas(int semana, int id_profesional, AgendaSchedulerModel modelo){
        ArrayList<Cita> citas = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM cita WHERE semana="+semana+" AND id_profesional="+id_profesional);
            citas = new ArrayList<Cita>();
            while(rs.next()){
                Paciente p = PacienteDB.getPaciente(rs.getInt("id_paciente"));
                Cita c = new Cita(p.getNombre()+" "+p.getApellido_pat());
                c.setFecha(rs.getString("fecha"));
                c.setSemana(rs.getInt("semana"));
                c.setProfesionalId(rs.getInt("id_profesional"));
                c.setPacienteId(p.getId_paciente());
                Date d = rs.getDate("fecha");
                Time t = rs.getTime("hora_inicio");
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                String[] hora = t.toString().split(":");
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
                cal.set(Calendar.SECOND, 0);
                //cal.set
                //d.setTime(t.getTime());
                DateTime dt = new DateTime(cal.getTime());
                c.setRealDateTime(dt);
                c.setDateTime(new DateTime());
                c.setDuration(new Duration(rs.getInt("duracion")));
                c.setResource(modelo.calcularResource(c.getRealDateTime().toDate()));
                System.out.println(c.getRealDateTime()+" "+c.getPacienteId()+" "+t.toString());
                citas.add(c);
            }
            rs.close();
            con.close();
            return citas;
            
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AgendaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return citas;
    }
        
}
