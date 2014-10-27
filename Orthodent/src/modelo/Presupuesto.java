/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class Presupuesto {
    

    private int idPresupuesto;
    private int idPaciente;
    private int idProfesional;
    private boolean estado;
    private int costoTotal;
    private int cantidadTratamiento;
    private boolean activo;    
    
    public Presupuesto(int idPresupuesto, int idPaciente, int idProfesional, boolean estado, int costoTotal, int cantidadTratamiento, boolean activo) {
        this.idPresupuesto = idPresupuesto;
        this.idPaciente = idPaciente;
        this.idProfesional = idProfesional;
        this.estado = estado;
        this.costoTotal = costoTotal;
        this.cantidadTratamiento = cantidadTratamiento;
        this.activo = activo;
    }    

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdProfesional() {
        return idProfesional;
    }

    public void setIdProfesional(int idProfesional) {
        this.idProfesional = idProfesional;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public int getCantidadTratamiento() {
        return cantidadTratamiento;
    }

    public void setCantidadTratamiento(int cantidadTratamiento) {
        this.cantidadTratamiento = cantidadTratamiento;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    
}
