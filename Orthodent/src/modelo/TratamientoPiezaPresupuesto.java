/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class TratamientoPiezaPresupuesto {

    private int id_tratamiento;
    private int id_presupuesto;
    private int pieza;
    
    public TratamientoPiezaPresupuesto(int id_tratamiento, int id_presupuesto, int pieza) {
        this.id_tratamiento = id_tratamiento;
        this.id_presupuesto = id_presupuesto;
        this.pieza = pieza;
    }

    public int getId_tratamiento() {
        return id_tratamiento;
    }

    public void setId_tratamiento(int id_tratamiento) {
        this.id_tratamiento = id_tratamiento;
    }

    public int getId_presupuesto() {
        return id_presupuesto;
    }

    public void setId_presupuesto(int id_presupuesto) {
        this.id_presupuesto = id_presupuesto;
    }

    public int getPieza() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza = pieza;
    }
}
