/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


/**
 *
 * @author msanhuezal
 */
public class Pago {

    private int idPlanTratamiento;
    private String fecha;
    private int abono;   
    
    public Pago(int idPlanTratamiento, String fecha, int abono) {
        this.idPlanTratamiento = idPlanTratamiento;
        this.fecha = fecha;
        this.abono = abono;
    }

    public int getIdPlanTratamiento() {
        return idPlanTratamiento;
    }

    public void setIdPlanTratamiento(int idPlanTratamiento) {
        this.idPlanTratamiento = idPlanTratamiento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getAbono() {
        return abono;
    }

    public void setAbono(int abono) {
        this.abono = abono;
    }
    

    
}
