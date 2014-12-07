/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Categoria2;
import orthodent.db.TratamientoDB;

/**
 *
 * @author msanhuezal
 */
public class Categoria2DB {
    
    public static boolean crearCategoria2(int idCategoria1, String nombre){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO categoria2 (id_categoria1, nombre)\n" +
                                        "VALUES ("+idCategoria1+",'"+nombre+"')");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static boolean editarCategoria2(int idCategoria, String nombre){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE categoria2\n" +
                                        "SET nombre='"+nombre+"'\n" +
                                        "WHERE id_categoria2="+idCategoria);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ArrayList<Categoria2> listarCategoria2(int idCategoria1){
        ArrayList<Categoria2> categorias2 = null;        
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * FROM categoria2 where activo="+1+" AND id_categoria1="+idCategoria1);
            categorias2 = new ArrayList<Categoria2>();
            while (rs.next())
            {
                Categoria2 t = new Categoria2(rs.getInt("id_categoria2"),rs.getInt("id_categoria1"), rs.getString("nombre"), rs.getBoolean("activo"));
                categorias2.add(t);
            }
            rs.close();
            con.close();
            return categorias2;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    static public Categoria2 getCategoria2(int idCategoria2) throws Exception{
        Categoria2 categoria2 = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from categoria2 where id_categoria2=" + idCategoria2);
            if (rs.next())
            {
                int id2 = rs.getInt("id_categoria2");
                int id1 = rs.getInt("id_categoria1");
                String nombre = rs.getString("nombre");
                Boolean activo = rs.getBoolean("activo");
                

                categoria2 = new Categoria2(id2,id1, nombre, activo);
            }
            else{
                categoria2 = null;
            }
            rs.close();
            con.close();
            return categoria2;
        }
        catch ( SQLException e) {
            return null;
        }
    }
    
    public static boolean eliminarCategoria2(int idCategoria2){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE categoria2\n" +
                                        "SET activo='"+0+"'\n" +
                                        "WHERE id_categoria2="+idCategoria2);
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }       

    public static boolean eliminarCategoria2DeCategoria1(int categoria1Selected) {
        try{
            //tengo todos las categorias 2 que pertenecen a una categoria 1 en especifico
            ArrayList<Categoria2> categorias2 = Categoria2DB.listarCategoria2(categoria1Selected);
            for(int i=0; i< categorias2.size();i++){
                //elimino la categoria2
                Categoria2DB.eliminarCategoria2(categorias2.get(i).getIdCategoria2());
                TratamientoDB.eliminarTratamientoCategoria2(categorias2.get(i).getIdCategoria2());


            } 
            return true;
        }
        catch ( Exception e) {
            return false;
        }
        
    }
    
}
