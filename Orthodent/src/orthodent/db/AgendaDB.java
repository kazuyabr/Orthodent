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
import modelo.Horario;
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
            String horaInicio = cita.getRealDateTime().toString("H:m");
            int duracion = cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes(); 
            int semana = cita.getSemana();
            String confirmada;
            
            if(cita.isConfirmada())
                confirmada = "True";
            else
                confirmada = "False";
            System.out.println("hora:"+horaInicio+"     fecha:"+fecha);
            int aux = st.executeUpdate("INSERT INTO cita (id_profesional, id_paciente, fecha, hora_inicio, duracion, semana, comentario, confirmada)\n"+
                    "VALUES ("+id_profesional+","+id_paciente+",'"+fecha+"','"+horaInicio+"',"+duracion+","+semana+",'Sin Comentario',"+confirmada+")");
            ResultSet rs = st.getGeneratedKeys();
            
            while(rs.next()){
                cita.setId(Integer.parseInt(rs.getObject(1).toString()));
            }
            
            System.out.println(aux);
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
                Cita c = new Cita(p.getNombre()+" "+p.getApellido_pat()+" "+p.getTelefono());
                c.setFecha(rs.getString("fecha"));
                c.setSemana(rs.getInt("semana"));
                c.setProfesionalId(rs.getInt("id_profesional"));
                c.setPacienteId(p.getId_paciente());
                c.setId(rs.getInt("id_cita"));
                c.setConfirmada(rs.getBoolean("confirmada"));
                Date d = rs.getDate("fecha");
                Time t = rs.getTime("hora_inicio");
                Calendar cal = Calendar.getInstance();
                cal.setTime(d);
                String[] hora = t.toString().split(":");
                
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hora[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(hora[1]));
                cal.set(Calendar.SECOND, 0);
                
                DateTime dt = new DateTime(cal.getTime());
                c.setRealDateTime(dt);
                c.setDateTime(new DateTime(modelo.obtenerLunes(cal.getTime())));
                c.setDuration(Duration.standardMinutes(rs.getInt("duracion")));
                c.setResource(modelo.calcularResource(c.getRealDateTime().toDate()));
                System.out.println(c.getRealDateTime()+" "+c.getDuration().toStandardSeconds().toStandardMinutes().getMinutes()+" "+c.getDateTime().toString());
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
    
    
    public static boolean eliminarCita(Cita cita){
        boolean resultado = false;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            
            int aux = st.executeUpdate("DELETE FROM cita WHERE id_cita="+cita.getId());
            resultado = (aux == 1)? true : false;
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static boolean actualizarCita(Cita cita){
        boolean resultado = false;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            
            int duracion = cita.getDuration().toStandardSeconds().toStandardMinutes().getMinutes();
            String horaInicio = cita.getRealDateTime().toString("h:m");
            
            String confirmada;
            if(cita.isConfirmada())
                confirmada="True";
            else confirmada="False";
            System.out.println(cita.getProfesionalId()+","+cita.getSemana()+","+cita.getFecha()+","+duracion+","+horaInicio+","+confirmada+","+cita.getId());
            int aux = st.executeUpdate("UPDATE cita SET id_profesional="+cita.getProfesionalId()+
                    ", semana="+cita.getSemana()+", fecha='"+cita.getFecha()+"', duracion="+duracion+", hora_inicio='"+horaInicio+"', confirmada="+confirmada+" WHERE id_cita="+cita.getId());
            resultado = (aux == 1)? true : false;
            System.out.println(resultado);
        } catch (SQLException ex) {
            Logger.getLogger(AgendaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    }
    
    public static ArrayList<Horario> obtenerHorario(String dia, int id_profesional){
        ArrayList<Horario> hor = null;
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            int inicio, fin, id;
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM horario WHERE dia='"+dia+"' AND id_usuario="+id_profesional);
            hor = new ArrayList<Horario>();
            while(rs.next()){
                id = rs.getInt("id_horario");
                inicio = rs.getInt("hora_inicio");
                fin = rs.getInt("hora_fin");
                Horario h = new Horario(id,id_profesional,dia,inicio,fin);
                hor.add(h);
            }
            rs.close();
            con.close();
        }
        catch(SQLException ex){
            Logger.getLogger(AgendaDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hor;
    }
        
}
