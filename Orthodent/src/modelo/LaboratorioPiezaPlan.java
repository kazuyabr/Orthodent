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
public class LaboratorioPiezaPlan {
    
    private int id;
    private int idPlanTratamiento;
    private int pieza;
    private String prestacion;
    private int valor;
    private String fechaRealizado;
    private boolean estado;

    public LaboratorioPiezaPlan(int id, int idPlanTratamiento, int pieza, String prestacion, int valor, String fechaRealizado, boolean estado) {
        this.id = id;
        this.idPlanTratamiento = idPlanTratamiento;
        this.pieza = pieza;
        this.prestacion = prestacion;
        this.valor = valor;
        this.fechaRealizado = fechaRealizado;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPlanTratamiento() {
        return idPlanTratamiento;
    }

    public void setIdPlanTratamiento(int idPlanTratamiento) {
        this.idPlanTratamiento = idPlanTratamiento;
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

    public String getFechaRealizado() {
        return fechaRealizado;
    }

    public void setFechaRealizado(String fechaRealizado) {
        this.fechaRealizado = fechaRealizado;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
