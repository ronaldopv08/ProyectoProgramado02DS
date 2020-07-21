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
    if(!validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoCifrado = "";
    pTexto = pTexto.toLowerCase();
    for(int i=0;i<pTexto.length();i++) {
      textoCifrado += desplazarLetra(pTexto.charAt(i),"derecha");
    }
    return textoCifrado;
  }

  @Override
  public String descifrar(String pTexto) {
    if(!validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoDescifrado = "";
    pTexto = pTexto.toLowerCase();
    for(int i=0;i<pTexto.length();i++) {
      textoDescifrado += desplazarLetra(pTexto.charAt(i),"izquierda");
    }
    return textoDescifrado;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    for (int i=0;i<pTexto.length();i++) {
      if (esCaracterValido(pTexto.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  private boolean esCaracterValido(char pCaracter) {
    return !Character.isAlphabetic(pCaracter) && !Character.isWhitespace(pCaracter);
  }
  
  private String desplazarLetra(char pCaracter,String direccion) {
    if (Character.isWhitespace(pCaracter)) {
      return " ";
    }
    String letra = "";
    double asciiCaracterDesplazado = 0;
    if (direccion=="derecha") {
      asciiCaracterDesplazado = pCaracter+Double.valueOf(clave);
    } else if (direccion=="izquierda") {
      asciiCaracterDesplazado = pCaracter-Double.valueOf(clave);
    } if (asciiCaracterDesplazado>122) {
      asciiCaracterDesplazado -= 26;
    } else if (asciiCaracterDesplazado<97) {
      asciiCaracterDesplazado += 26;
    }
    char caracterDesplazado = (char) asciiCaracterDesplazado;
    return letra+caracterDesplazado;
  }

}
