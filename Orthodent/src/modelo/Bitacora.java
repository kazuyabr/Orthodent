/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author felipe
 */
public class Bitacora 
{
    private int idBitacora;
    private String accion;
    private int idUsuario;
    private String tabla;
    private int pk;
    private String fecha;

    public Bitacora(int idBitacora, String accion, int idUsuario, String tabla, int pk, String fecha) {
        this.idBitacora = idBitacora;
        this.idUsuario = idUsuario; 
        this.accion = accion;
        this.tabla = tabla;
        this.pk = pk;
        this.fecha = fecha;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int id) {
        this.idBitacora = idBitacora;
    }

    public int getId_usuario() {
        return idUsuario;
    }

    public void setId_usuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }

    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
