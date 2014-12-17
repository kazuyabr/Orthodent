/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package orthodent.utils;

import java.awt.event.KeyEvent;

/**
 *
 * @author felipe
 */
public class Validaciones {
    public static void validarRut(String prev_rut, java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        
        if(c==KeyEvent.VK_ENTER){
            evt.consume();
        }
        else if(!((c>='0' && c<='9') || (c=='-') || (c=='k') || (c=='K'))){
            evt.consume();
        }
        else {
            if(c=='k' || c=='K'){
                String ultimo = prev_rut.length() > 0? prev_rut.charAt(prev_rut.length()-1)+"" : "";
                if(!ultimo.equals("-")){
                    evt.consume();
                }
            }
            if(c=='-'){
                int largo = prev_rut.length();
                String ultimo = prev_rut.length() > 0? prev_rut.charAt(prev_rut.length()-1)+"" : "";
                if(!(largo==7 || largo==8) || ultimo.equals("-")){
                    evt.consume();
                }
            }
            if(c>='0' && c<='9'){
                int largo = prev_rut.length();
                
                if(!prev_rut.contains("-")){
                    if(!(largo<8)){
                        evt.consume();
                    }
                }
                else{
                    char ultimo = prev_rut.length() > 0? prev_rut.charAt(prev_rut.length()-1) : '|';
                    if((ultimo>='0' && ultimo<='9') || ultimo=='k' || ultimo=='K'){
                        evt.consume();
                    }
                }
            }
        }
    }
    
    static public String calcularDigitoVerificador(String rut){
        int i = rut.length()-1;
        int j = 7;
        int suma = 0;
        int array [] = {8,9,4,5,6,7,8,9};
        
        while(i>=0){
            suma = suma + Integer.parseInt(rut.charAt(i)+"")*array[j];
            i--;
            j--;
        }
        
        if(suma%11==10){
            return "k";
        }
        else{
            return (suma%11)+"";
        }
    }
    
    static public void validarNombre(java.awt.event.KeyEvent evt) {                                 
        char c = evt.getKeyChar();
        if(!(Character.isSpaceChar(c)||Character.isAlphabetic(c))) {
            evt.consume();
        }
    }
}
