/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class Comuna {
    
    int codigo;
    String nombre;
    int codPadre;    

    public Comuna(int codigo, String nombre, int codPadre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codPadre = codPadre;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodPadre() {
        return codPadre;
    }

    public void setCodPadre(int codPadre) {
        this.codPadre = codPadre;
    }
    

    
}
