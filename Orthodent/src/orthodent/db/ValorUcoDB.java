/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ValorUco;

/**
 *
 * @author msanhuezal
 */
public class ValorUcoDB {
    
    
    public static boolean crearValorUco(int valor, int porcentaje){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("INSERT INTO valor_uco (valor, porcentaje)\n" +
                                        "VALUES ("+valor+","+porcentaje+")");
            boolean resultado = (aux == 1)? true : false;
            st.close();
            con.close();
            return resultado;
        }
        catch ( SQLException e) {
            return false;
        }
    }
    
    public static ValorUco getValorUco(int idValorUco) throws Exception{
        ValorUco valorUco = null;
        try {
            DbConnection db = new DbConnection();
            Connection con = db.getConnection();
            
            java.sql.Statement st = con.createStatement();
            
            ResultSet rs = st.executeQuery("SELECT * from valor_uco where id_valor_uco=" + idValorUco);
            if (rs.next())
            {
                int id = rs.getInt("id_valor_uco");
                int valor = rs.getInt("valor");
                int porcentaje = rs.getInt("porcentaje");

                valorUco = new ValorUco(id, valor, porcentaje);
            }
            else{
                valorUco = null;
            }
            rs.close();
            con.close();
            return valorUco;
        }
        catch ( SQLException e) {
            return null;
        }
    }    

    public static boolean editarValorUco(ValorUco valorUco){
        try{
            DbConnection db = new DbConnection();
            Connection con = db.connection;
            
            java.sql.Statement st = con.createStatement();
            int aux = st.executeUpdate("UPDATE valor_uco\n" +
                                            "SET valor="+valorUco.getValor()+"\n" +
                                            ",porcentaje="+valorUco.getPorcentaje()+"\n" +
                                            "WHERE id_valor_uco="+valorUco.getIdValorUco());
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
