/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class TratamientoPiezaPlan {

    private int idTratamientoPiezaPlan;
    private int IdPlanTratamiento;
    private int idTratamiento;
    private int pieza;
    private String fechaRealizado;
    private boolean estado;    
    
    
    public TratamientoPiezaPlan(int idTratamientoPiezaPlan, int IdPlanTratamiento, int idTratamiento, int pieza, String fechaRealizado, boolean estado) {
        this.idTratamientoPiezaPlan = idTratamientoPiezaPlan;
        this.IdPlanTratamiento = IdPlanTratamiento;
        this.idTratamiento = idTratamiento;
        this.pieza = pieza;
        this.fechaRealizado = fechaRealizado;
        this.estado = estado;
    }

    public int getIdTratamientoPiezaPlan() {
        return idTratamientoPiezaPlan;
    }

    public void setIdTratamientoPiezaPlan(int idTratamientoPiezaPlan) {
        this.idTratamientoPiezaPlan = idTratamientoPiezaPlan;
    }

    public int getIdPlanTratamiento() {
        return IdPlanTratamiento;
    }

    public void setIdPlanTratamiento(int IdPlanTratamiento) {
        this.IdPlanTratamiento = IdPlanTratamiento;
    }

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }

    public int getPieza() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza = pieza;
    }

    public String getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(String fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    

    
}
