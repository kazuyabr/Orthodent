/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mary
 */
public class LaboratorioPiezaPresupuesto {
    
    private int id;
    private int idPresupuesto;
    private int pieza;
    private String prestacion;
    private int valor;

    public LaboratorioPiezaPresupuesto(int id, int idPresupuesto, int pieza, String prestacion, int valor) {
        this.id = id;
        this.idPresupuesto = idPresupuesto;
        this.pieza = pieza;
        this.prestacion = prestacion;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPresupuesto() {
        return idPresupuesto;
    }

    public void setIdPresupuesto(int idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }

    public int getPieza() {
        return pieza;
    }

    public void setPieza(int pieza) {
        this.pieza = pieza;
    }

    public String getPrestacion() {
        return prestacion;
    }

    public void setPrestacion(String prestacion) {
        this.prestacion = prestacion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
