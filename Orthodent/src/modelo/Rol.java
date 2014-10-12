/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mary
 */
public class Rol {
    
    int id_rol;
    String nombre;
    
    public Rol(int id_rol, String nombre){
        this.id_rol = id_rol;
        this.nombre = nombre;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
