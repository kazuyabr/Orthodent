/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class PlanTratamiento {

    private int idPlanTratamiento;
    private int idPaciente;
    private int idProfesional;
    private String fechaCreacionPresupuesto;
    private String fechaModificacionPresupuesto;
    private int costoTotal;
    private int totalAbonos;
    private int avance;
    private boolean activo;    
    
    public PlanTratamiento(int idPlanTratamiento, int idPaciente, int idProfesional, String fechaCreacionPresupuesto, String fechaModificacionPresupuesto, int costoTotal, int totalAbonos, int avance, boolean activo) {
        this.idPlanTratamiento = idPlanTratamiento;
        this.idPaciente = idPaciente;
        this.idProfesional = idProfesional;
        this.fechaCreacionPresupuesto = fechaCreacionPresupuesto;
        this.fechaModificacionPresupuesto = fechaModificacionPresupuesto;
        this.costoTotal = costoTotal;
        this.totalAbonos = totalAbonos;
        this.avance = avance;
        this.activo = activo;
    }

    public int getIdPlanTratamiento() {
        return idPlanTratamiento;
    }

    public void setIdPlanTratamiento(int idPlanTratamiento) {
        this.idPlanTratamiento = idPlanTratamiento;
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

    public String getFechaCreacionPresupuesto() {
        return fechaCreacionPresupuesto;
    }

    public void setFechaCreacionPresupuesto(String fechaCreacionPresupuesto) {
        this.fechaCreacionPresupuesto = fechaCreacionPresupuesto;
    }

    public String getFechaModificacionPresupuesto() {
        return fechaModificacionPresupuesto;
    }

    public void setFechaModificacionPresupuesto(String fechaModificacionPresupuesto) {
        this.fechaModificacionPresupuesto = fechaModificacionPresupuesto;
    }

    public int getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(int costoTotal) {
        this.costoTotal = costoTotal;
    }

    public int getTotalAbonos() {
        return totalAbonos;
    }

    public void setTotalAbonos(int totalAbonos) {
        this.totalAbonos = totalAbonos;
    }

    public int getAvance() {
        return avance;
    }

    public void setAvance(int avance) {
        this.avance = avance;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
