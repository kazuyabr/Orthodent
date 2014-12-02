/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Categoria1;

/**
 *
 * @author msanhuezal
 */
public class Categoria1DB {
    
    public static boolean crearCategoria1(String nombre){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO categoria1 (nombre)\n" +
                                        "VALUES ('"+nombre+"')");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarCategoria1(int idCategoria, String nombre){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE categoria1\n" +
                                        "SET nombre='"+nombre+"'\n" +
                                        "WHERE id_categoria1="+idCategoria);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ArrayList<Categoria1> listarCategoria1(){
        ArrayList<Categoria1> categorias1 = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM categoria1 where activo="+1);
            categorias1 = new ArrayList<Categoria1>();
            while (rs.next())
            {
                Categoria1 t = new Categoria1(rs.getInt("id_categoria1"), rs.getString("nombre"), rs.getBoolean("activo"));
                categorias1.add(t);
            }
            rs.close();
            con.close();
            return categorias1;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    static public Categoria1 getCategoria1(int idCategoria1) throws Exception{
        Categoria1 categoria1 = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from categoria1 where id_categoria1=" + idCategoria1);
            if (rs.next())
            {
                int id = rs.getInt("id_categoria1");
                String nombre = rs.getString("nombre");
                Boolean activo = rs.getBoolean("activo");

                categoria1 = new Categoria1(id, nombre, activo);
            }
            else{
                categoria1 = null;
            }
            rs.close();
            con.close();
            return categoria1;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean eliminarCategoria1(int idCategoria1){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE categoria1\n" +
                                        "SET activo='"+0+"'\n" +
                                        "WHERE id_categoria1="+idCategoria1);
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
