/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Horario;

/**
 *
 * @author Mary
 */
public class HorarioDB {
    
    public static ArrayList<Horario> getHorarios(int id_usuario){
        ArrayList<Horario> horarios = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM horario WHERE id_usuario="+id_usuario);
            horarios = new ArrayList<Horario>();
            while (rs.next())
            {
                Horario o = new Horario(rs.getInt("id_horario"), rs.getInt("id_usuario"),
                        rs.getString("dia"), rs.getInt("hora_inicio"), rs.getInt("hora_fin"));
                horarios.add(o);
            }
            rs.close();
            con.close();
            return horarios;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean eliminarHorario(int id_usuario) throws SQLException{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;

            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("DELETE FROM horario "+
                                            "WHERE id_usuario="+id_usuario);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) { 
            return false;
        } 
    }
    
    public static boolean crearHorario(int id_usuario, String dia, int hora_inicio, int hora_fin){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;

            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO horario (id_usuario, dia, hora_inicio, hora_fin)\n" +
                                        "VALUES ("+id_usuario+",'"+dia+"',"+hora_inicio+","+hora_fin+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();             
            return resultado;           
        }
        catch ( SQLException e) { 
            return false;
        }             

    }
}
