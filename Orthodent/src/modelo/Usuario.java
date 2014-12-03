/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class Usuario {

    private int id_usuario;
    private int id_rol;
    private int id_clinica;
    private String nombre;
    private String apellido_pat;
    private String apellido_mat;
    private String nombreUsuario;
    private String contraseña;
    private String email;
    private String telefono;
    private String especialidad;
    private int tiempoCita;
    private boolean activo;
    
   //constructor PROFESIONAL 
   public Usuario(int id_usuario, int id_rol, String nombre, String apellido_pat, String apellido_mat, String nombreUsuario, String contraseña, String email, String telefono, String especialidad, int tiempoCita, boolean activo, int id_clinica) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido_pat = apellido_pat;
        this.apellido_mat = apellido_mat;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.email = email;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.tiempoCita = tiempoCita;
        this.activo = activo;
        this.id_clinica = id_clinica;
    }    
    
    //constructor usuarios ASISTENTE Y ADMINISTRADOR
    public Usuario(int id_usuario, int id_rol, String nombre, String apellido_pat, String apellido_mat, String nombreUsuario, String contraseña, String email, String telefono, boolean activo) {
        this.id_usuario = id_usuario;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido_pat = apellido_pat;
        this.apellido_mat = apellido_mat;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.email = email;
        this.telefono = telefono;
        this.activo = activo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }    
    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
    
    public int getTiempoCita() {
        return tiempoCita;
    }

    public void setTiempoCita(int tiempoCita) {
        this.tiempoCita = tiempoCita;
    }

    public int getId_clinica() {
        return id_clinica;
    }

    public void setId_clinica(int id_clinica) {
        this.id_clinica = id_clinica;
    }    
}
