/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.LaboratorioPiezaPresupuesto;

/**
 *
 * @author msanhuezal
 */
public class LaboratorioPiezaPresupuestoDB {
    
    public static boolean crearLaboratorioPiezaPresupuesto(int id_presupuesto, int pieza, String prestacion, int valor){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO laboratorio_piezapresupuesto (id_presupuesto, pieza, prestacion, valor)\n" +
                                        "VALUES ("+id_presupuesto+","+pieza+",'"+prestacion+"',"+valor+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }  
    
    public static ArrayList<LaboratorioPiezaPresupuesto> listarLaboratoriosPiezaPresupuesto(int id_presupuesto){
        ArrayList<LaboratorioPiezaPresupuesto> laboratoriosPP = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM laboratorio_piezapresupuesto WHERE id_presupuesto="+id_presupuesto);
            laboratoriosPP = new ArrayList<LaboratorioPiezaPresupuesto>();
            while (rs.next())
            {
                LaboratorioPiezaPresupuesto t = new LaboratorioPiezaPresupuesto(rs.getInt("id"), rs.getInt("id_presupuesto"), rs.getInt("pieza"), rs.getString("prestacion"), rs.getInt("valor"));
                laboratoriosPP.add(t);
            }
            rs.close();
            con.close();
            return laboratoriosPP;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean eliminarLaboratorioPieza(int id_presupuesto) throws SQLException{
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("DELETE FROM laboratorio_piezapresupuesto\n" +
                                            "WHERE id_presupuesto="+id_presupuesto);
            st.close();
            con.close();
            return true;
        }
        catch ( SQLException e) {
            return false;
        }
    }
}
