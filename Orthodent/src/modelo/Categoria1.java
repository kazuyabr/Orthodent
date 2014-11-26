/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class Categoria1 {

    public Categoria1(int idCategoria1, String nombre, boolean activo) {
        this.idCategoria1 = idCategoria1;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getIdCategoria1() {
        return idCategoria1;
    }

    public void setIdCategoria1(int idCategoria1) {
        this.idCategoria1 = idCategoria1;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
    
    private int idCategoria1;
    private String nombre;
    private boolean activo;
    
}
