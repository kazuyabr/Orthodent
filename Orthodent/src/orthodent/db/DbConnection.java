/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.db;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author msanhuezal
 */
/**
 * Clase que permite conectar con la base de datos
 *
 */
public class DbConnection {
   /**Parametros de conexion*/
   static String bd = "casino_utal";
   static String login = "root";
   static String password = "";
   static String url = "jdbc:mysql://localhost/"+bd;
 
   Connection connection = null;
 
   /** Constructor de DbConnection */
   public DbConnection() {
      try{
         //obtenemos el driver de para mysql
         Class.forName("com.mysql.jdbc.Driver");
         //obtenemos la conexión
         connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/clinica","root", "");
         
         if (connection!=null){
            System.out.println("Conexión a base de datos " + bd + " OK\n");
         }
      }
      catch(SQLException e){
         System.out.println(e);
      }catch(ClassNotFoundException e){
         System.out.println(e);
      }catch(Exception e){
         System.out.println(e);
      }
   }
   
   /**Permite retornar la conexión*/
   public Connection getConnection(){
      return connection;
   }
   /**Permite desconectar*/
   public void desconectar(){
      connection = null;
   }
}
