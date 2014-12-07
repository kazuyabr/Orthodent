/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.LaboratorioPiezaPlan;

/**
 *
 * @author msanhuezal
 */
public class LaboratorioPiezaPlanDB {
    
    public static boolean crearLaboratorioPiezaPlan(int idPlanTratamiento, int pieza, String prestacion, int valor, boolean estado){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO laboratorio_piezaplan (id_plantratamiento, pieza, prestacion, valor, estado)\n" +
                                        "VALUES ("+idPlanTratamiento+","+pieza+",'"+prestacion+"',"+valor+","+estado+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }  
    
    public static boolean editarLaboratorioPiezaPlan(LaboratorioPiezaPlan laboratorioPiezaPlan){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE laboratorio_piezaplan\n" +
                                            "SET id_plantratamiento = "+laboratorioPiezaPlan.getIdPlanTratamiento()+"\n" +
                                            ",pieza="+laboratorioPiezaPlan.getPieza()+"\n" +
                                            ",prestacion='"+laboratorioPiezaPlan.getPrestacion()+"'\n" +
                                            ",valor="+laboratorioPiezaPlan.getValor()+"\n" +
                                            ",fecha_realizado='"+laboratorioPiezaPlan.getFechaRealizado()+"'\n" +
                                            ",estado="+laboratorioPiezaPlan.getEstado()+"\n" +
                                            "WHERE id="+laboratorioPiezaPlan.getId());
            
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ArrayList<LaboratorioPiezaPlan> listarLaboratoriosPiezaPlan(){
        ArrayList<LaboratorioPiezaPlan> laboratoriosPiezaPlan = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM laboratorio_piezaplan");
            laboratoriosPiezaPlan = new ArrayList<LaboratorioPiezaPlan>();
            while (rs.next())
            {
                LaboratorioPiezaPlan t = new LaboratorioPiezaPlan(rs.getInt("id"), rs.getInt("id_plantratamiento"),
                                            rs.getInt("pieza"), rs.getString("prestacion"), rs.getInt("valor"),
                                            rs.getString("fecha_realizado"), rs.getBoolean("estado"));
                laboratoriosPiezaPlan.add(t);
            }
            rs.close();
            con.close();
            return laboratoriosPiezaPlan;
        }
        catch ( SQLException e) {
            return null;
        }
    }   
    
    public static ArrayList<LaboratorioPiezaPlan> listarLaboratoriosPiezaPlan(int idPlanTratamiento){ // listar tratamiento pieza plan por plan de tratamiento
        ArrayList<LaboratorioPiezaPlan> laboratoriosPiezaPlan = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM laboratorio_piezaplan where id_plantratamiento=" + idPlanTratamiento);
            laboratoriosPiezaPlan = new ArrayList<LaboratorioPiezaPlan>();
            while (rs.next())
            {
                LaboratorioPiezaPlan t = new LaboratorioPiezaPlan(rs.getInt("id"), rs.getInt("id_plantratamiento"),
                                            rs.getInt("pieza"), rs.getString("prestacion"), rs.getInt("valor"),
                                            rs.getString("fecha_realizado"), rs.getBoolean("estado"));
                laboratoriosPiezaPlan.add(t);
            }
            rs.close();
            con.close();
            return laboratoriosPiezaPlan;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    static public LaboratorioPiezaPlan getLaboratorioPiezaPlan(int idLaboratorioPiezaPlan) throws Exception{
        LaboratorioPiezaPlan laboratorioPiezaPlan = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from laboratorio_piezaplan where id=" + idLaboratorioPiezaPlan);
            if (rs.next())
            {
                int idPlanTratamiento = rs.getInt("id_plantratamiento");
                int pieza = rs.getInt("pieza");
                String prestacion = rs.getString("prestacion");
                int valor = rs.getInt("valor");
                String fechaRealizado = rs.getString("fecha_realizado");
                boolean estado = rs.getBoolean("estado");

                laboratorioPiezaPlan = new LaboratorioPiezaPlan(idLaboratorioPiezaPlan, idPlanTratamiento, pieza, prestacion, valor, fechaRealizado, estado);
            }
            else{
                laboratorioPiezaPlan = null;
            }
            rs.close();
            con.close();
            return laboratorioPiezaPlan;
        }
        catch ( SQLException e) {
            return null;
        }
    }
}
