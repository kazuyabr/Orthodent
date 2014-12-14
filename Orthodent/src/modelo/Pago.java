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
    
    private int idPago;
    private int idPlanTratamiento;
    private String tipoPago;
    private String detalle;
    private String fecha;
    private int abono;   
    private int numBoleta;
    private boolean isLab;
    
    public Pago(int idPago, int idPlanTratamiento, String tipoPago, String detalle, String fecha, int abono, int numBoleta, boolean isLab) {
        this.idPago = idPago;
        this.idPlanTratamiento = idPlanTratamiento;
        this.tipoPago = tipoPago;
        this.detalle = detalle;
        this.fecha = fecha;
        this.abono = abono;
        this.numBoleta = numBoleta;
        this.isLab = isLab;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
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

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getNumBoleta() {
        return numBoleta;
    }

    public void setNumBoleta(int numBoleta) {
        this.numBoleta = numBoleta;
    }

    public boolean getIsLab() {
        return isLab;
    }

    public void setIsLab(boolean isLab) {
        this.isLab = isLab;
    }
}
