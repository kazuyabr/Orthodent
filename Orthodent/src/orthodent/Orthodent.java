/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent;

import de.javasoft.plaf.synthetica.SyntheticaBlueLightLookAndFeel;
import java.sql.Date;
import java.sql.SQLException;

import javax.swing.UIManager;
import modelo.Paciente;
import modelo.Usuario;
import orthodent.db.Autenticacion;
import orthodent.db.PacienteDB;

/**
 *
 * @author Mary
 */
public class Orthodent {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{

        try{
            UIManager.setLookAndFeel(new SyntheticaBlueLightLookAndFeel()); // cambia la apariencia de la ventana
        }
        catch(Exception e){
        }
        
        new Login(null,true).setVisible(true);
    }
}
