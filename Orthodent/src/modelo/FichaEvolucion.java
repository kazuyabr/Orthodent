/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class FichaEvolucion {

    private int idFichaEvolucion;
    private int idPlanTratamiento;
    private String fechaCita;
    private String descripcion;    
    
    public FichaEvolucion(int idFichaEvolucion, int idPlanTratamiento, String fechaCita, String descripcion) {
        this.idFichaEvolucion = idFichaEvolucion;
        this.idPlanTratamiento = idPlanTratamiento;
        this.fechaCita = fechaCita;
        this.descripcion = descripcion;
    }

    public int getIdFichaEvolucion() {
        return idFichaEvolucion;
    }

    public void setIdFichaEvolucion(int idFichaEvolucion) {
        this.idFichaEvolucion = idFichaEvolucion;
    }    
    
    public int getIdPlanTratamiento() {
        return idPlanTratamiento;
    }

    public void setIdPlanTratamiento(int idPlanTratamiento) {
        this.idPlanTratamiento = idPlanTratamiento;
    }

    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    

    
    
}
