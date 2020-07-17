package logicadenegocios;

/**
 * Clase simple para cifrar utilizando el algoritmo de sustitución Vigenére
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class Vigenere extends Sustitucion {
    
  /**
   * Método constructor de la clase, no recibe parámetros.
   */
  public Vigenere() {

  }

  @Override
  public String cifrar(String pTexto) {
    if(!validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoCifrado = "";
    pTexto = pTexto.toLowerCase();
    for(String palabra : pTexto.split(" ")) {
      for (int i=0;i<palabra.length();i++) {
        if (i%2==0) {
          textoCifrado += desplazarLetra(palabra.charAt(i), Double.valueOf(clave)/10);
        }else {
          textoCifrado += desplazarLetra(palabra.charAt(i), Double.valueOf(clave)%10);
        }
      }
      textoCifrado += " ";
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
    for (String palabra : pTexto.split(" ")) {
      for (int i=0;i<palabra.length();i++) {
        if (i%2==0) {
          textoDescifrado += desplazarLetra(palabra.charAt(i), -Double.valueOf(clave)/10);
        }else {
          textoDescifrado += desplazarLetra(palabra.charAt(i), -Double.valueOf(clave)%10);
        }
      }
      textoDescifrado += " ";
    }
    return textoDescifrado;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    for (int i=0;i<pTexto.length();i++) {
      if (!Character.isAlphabetic(pTexto.charAt(i)) 
          && !Character.isWhitespace(pTexto.charAt(i))) {
        return false;
      }
    }
    return true;
  }
  
  private String desplazarLetra(char c, double desplazamiento) {
    String letra = "";
    int asciiCaracterDesplazado = 0;
    asciiCaracterDesplazado = c+ (int)desplazamiento;
    if (asciiCaracterDesplazado>122) {
      asciiCaracterDesplazado -= 26;
    } else if (asciiCaracterDesplazado<97) {
      asciiCaracterDesplazado += 26;
    }
    char caracterDesplazado = (char) asciiCaracterDesplazado;
    return letra+caracterDesplazado;
  }

}
