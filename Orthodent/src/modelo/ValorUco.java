/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author msanhuezal
 */
public class ValorUco {
    
    private int idValorUco;
    private int valor;
    private int porcentaje;
    
    public ValorUco(int idValorUco, int valor, int porcentaje) {
        this.idValorUco = idValorUco;
        this.valor = valor;
        this.porcentaje = porcentaje;
    }

    public int getIdValorUco() {
        return idValorUco;
    }

    public void setIdValorUco(int idValorUco) {
        this.idValorUco = idValorUco;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
