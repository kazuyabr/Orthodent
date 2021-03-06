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
    private int costoLab;
    private String fechaCreacion;
    private String fechaModificacion;
    private boolean activo;
    
    public Presupuesto(int idPresupuesto, int idPaciente, int idProfesional, boolean estado, int costoTotal, int cantidadTratamiento, int costoLab, boolean activo, String fechaCreacion, String fechaModificacion) {
        this.idPresupuesto = idPresupuesto;
        this.idPaciente = idPaciente;
        this.idProfesional = idProfesional;
        this.estado = estado;
        this.costoTotal = costoTotal;
        this.cantidadTratamiento = cantidadTratamiento;
        this.costoLab = costoLab;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }    
    
    public Presupuesto(int idPresupuesto, int idPaciente, int idProfesional, boolean estado, int costoTotal, int cantidadTratamiento, int costoLab, boolean activo, String fechaCreacion) {
        this.idPresupuesto = idPresupuesto;
        this.idPaciente = idPaciente;
        this.idProfesional = idProfesional;
        this.estado = estado;
        this.costoTotal = costoTotal;
        this.cantidadTratamiento = cantidadTratamiento;
        this.costoLab = costoLab;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
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

    public boolean getEstado() {
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

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public int getCostoLab() {
        return costoLab;
    }

    public void setCostoLab(int costoLab) {
        this.costoLab = costoLab;
    }
}