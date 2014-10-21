/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Mary
 */
public class Horario {
    
    private int id_horario;
    private int id_usuario;
    private String dia;
    private int hora_inicio;//minutos pasados desde las 00:00
    private int hora_fin;//minutos pasados desde las 00:00

    public Horario(int id_horario, int id_usuario, String dia, int hora_inicio, int hora_fin) {
        this.id_horario = id_horario;
        this.id_usuario = id_usuario;
        this.dia = dia;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    private int getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(int hora_fin) {
        this.hora_fin = hora_fin;
    }

    private int getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(int hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    
    private String getHora(int min){
        
        int horaAux = min/60;
        String hora = "";
        
        if(horaAux<=9){
            hora = hora + "0";
        }
        
        hora = hora + horaAux;
        
        return hora;
    }
    
    private String getMin(int minAux){
        
        String min = "";
        
        if(minAux<9){
            min = min + "0";
        }
        
        min = min + minAux;
        
        return min;
    }
    
    public String getHoraInicio(){
        int inicio = this.getHora_inicio();
        String horaInicio = this.getHora(inicio);
        
        return horaInicio;
    }
    
    public String getMinInicio(){
        int inicio = this.getHora_inicio();
        inicio = inicio%60;
        String minInicio = this.getMin(inicio);
        
        return minInicio;
    }
    
    public String getHoraFin(){
        int fin = this.getHora_fin();
        String horaFin = this.getHora(fin);
        
        return horaFin;
    }
    
    public String getMinFin(){
        int fin = this.getHora_fin();
        fin = fin%60;
        String minFin = this.getMin(fin);
        
        return minFin;
    }
}
