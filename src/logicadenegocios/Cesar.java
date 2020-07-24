package logicadenegocios;

import logicadenegocios.Sustitucion;

/**
 * Clase simple para cifrar por medio del algoritmo de sustitución César
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class Cesar extends Sustitucion {
  
  /**
   * Método constructor de la clase, sin parámetros
   */
  public Cesar() {
  }

  @Override
  public String cifrar(String pTexto) {
    pTexto = pTexto.toLowerCase();
    if(!validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoCifrado = "";
    for(int i=0;i<pTexto.length();i++) {
      textoCifrado += desplazarLetra(pTexto.charAt(i),Double.valueOf(clave));
    }
    return textoCifrado;
  }

  @Override
  public String descifrar(String pTexto) {
    pTexto = pTexto.toLowerCase();
    if(!validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoDescifrado = "";
    for(int i=0;i<pTexto.length();i++) {
      textoDescifrado += desplazarLetra(pTexto.charAt(i),26-Double.valueOf(clave));
    }
    return textoDescifrado;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    if (pTexto.isEmpty()) {
      return false;
    }
    return pTexto.matches("[a-z\\ ]*"); 
  }
  
  private String desplazarLetra(char pCaracter,double desplazamiento) {
    if (Character.isWhitespace(pCaracter)) {
      return " ";
    }
    int posicion = (int) calcularDesplazamiento(caracteres.indexOf(pCaracter),desplazamiento);
    return String.valueOf(caracteres.charAt(posicion));
  }
  
  private double calcularDesplazamiento(int pPosicion,double pDesplazamiento) {
    return (pPosicion+pDesplazamiento)%26;
  }
  
}
