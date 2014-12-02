/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class Tratamiento {
    
    private int idTratamiento;
    private int idCategoria2;
    private String nombre;
    private float cantidadUCO;
    private int valorColegio;
    private int valorClinica;  
    private boolean activo;
    
    public Tratamiento(int idTratamiento, int idCategoria2, String nombre, float cantidadUCO, int valorColegio, int valorClinica, boolean activo) {
        this.idTratamiento = idTratamiento;
        this.idCategoria2 = idCategoria2;
        this.nombre = nombre;
        this.cantidadUCO = cantidadUCO;
        this.valorColegio = valorColegio;
        this.valorClinica = valorClinica;
        this.activo = activo;
    }    

    public int getIdTratamiento() {
        return idTratamiento;
    }

    public void setIdTratamiento(int idTratamiento) {
        this.idTratamiento = idTratamiento;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getValorColegio() {
        return valorColegio;
    }

    public void setValorColegio(int valorColegio) {
        this.valorColegio = valorColegio;
    }

    public int getValorClinica() {
        return valorClinica;
    }

    public void setValorClinica(int valorClinica) {
        this.valorClinica = valorClinica;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getIdCategoria2() {
        return idCategoria2;
    }

    public void setIdCategoria2(int idCategoria2) {
        this.idCategoria2 = idCategoria2;
    }

    public float getCantidadUCO() {
        return cantidadUCO;
    }

    public void setCantidadUCO(float cantidadUCO) {
        this.cantidadUCO = cantidadUCO;
    }
}
