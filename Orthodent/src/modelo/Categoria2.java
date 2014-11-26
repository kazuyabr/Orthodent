/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class Categoria2 {

    public Categoria2(int idCategoria2, int idCategoria1, String nombre, boolean activo) {
        this.idCategoria2 = idCategoria2;
        this.idCategoria1 = idCategoria1;
        this.nombre = nombre;
        this.activo = activo;
    }

    public int getIdCategoria2() {
        return idCategoria2;
    }

    public void setIdCategoria2(int idCategoria2) {
        this.idCategoria2 = idCategoria2;
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
    
    private int idCategoria2;
    private int idCategoria1;
    private String nombre;
    private boolean activo;
    
}
