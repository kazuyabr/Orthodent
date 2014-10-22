/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author msanhuezal
 */
public class Paciente {

    private int id_paciente;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private String rut;
    private String email;
    private String fechaNacimiento;
    private int sexo;//1 femenino, 2 masculino
    private String antecedenteMedico;
    private String telefono;
    private String ciudad;
    private String comuna;
    private String direccion;
    private boolean activo; 
    
    public Paciente(int id_paciente, String nombre, String apellido_pat, String apellido_mat, String rut, String email, String fechaNacimiento, int sexo, String antecedenteMedico, String telefono, String ciudad, String comuna, String direccion, boolean activo) {
        this.id_paciente = id_paciente;
        this.nombre = nombre;
        this.apellido_pat = apellido_pat;
        this.apellido_mat = apellido_mat;
        this.rut = rut;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.antecedenteMedico = antecedenteMedico;
        this.telefono = telefono;
        this.ciudad = ciudad;
        this.comuna = comuna;
        this.direccion = direccion;
        this.activo = activo;
    }
    
    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_pat() {
        return apellido_pat;
    }

    public void setApellido_pat(String apellido_pat) {
        this.apellido_pat = apellido_pat;
    }

    public String getApellido_mat() {
        return apellido_mat;
    }

    public void setApellido_mat(String apellido_mat) {
        this.apellido_mat = apellido_mat;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getAntecedenteMedico() {
        return antecedenteMedico;
    }

    public void setAntecedenteMedico(String antecedenteMedico) {
        this.antecedenteMedico = antecedenteMedico;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }
}
