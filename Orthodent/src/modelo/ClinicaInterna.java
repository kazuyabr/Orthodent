/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Lucy
 */
public class ClinicaInterna {
    
    private String nombre;
    private int id;
    private boolean activo;

    public ClinicaInterna(String nombre, int id, boolean activo) {
        this.nombre = nombre;
        this.id = id;
        this.activo = activo;
    }
    
    public ClinicaInterna(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }    
    
    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    public ClinicaInterna(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
