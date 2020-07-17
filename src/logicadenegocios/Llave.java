package logicadenegocios;

/**
 * Clase simple para cifrar por Llave
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public class Llave extends Sustitucion {
  
  /**
   * Método constructor de la clase, sin parámetros
   */
  public Llave() {   
  }

  @Override
  public String cifrar(String pTexto) {
    if(validarMensaje(pTexto) || pTexto.isEmpty()){
      return "N/A";
    }
    String nuevoTexto = "";
    for(String i : pTexto.split(" ")) {
      int posicionClave = 0;
      for(int j=0; j<i.length();j++) {
        if (posicionClave == clave.length()) {
          posicionClave = 0;
        }
        int posicion = caracteres.indexOf(Character.toString(clave.charAt(posicionClave))) + 
            caracteres.indexOf(Character.toString(i.charAt(j))) + 1;
        nuevoTexto += obtenerCaracter(posicion); 
        posicionClave++;
      }
      nuevoTexto += " ";
    }
    return nuevoTexto;
  }

  @Override
  public String descifrar(String pTexto) {
    if(validarMensaje(pTexto) || pTexto.isEmpty()){
      return "N/A";
    }
    String nuevoTexto = "";
    for(String i : pTexto.split(" ")) {
      int posicionClave = 0;
      for(int j=0; j<i.length();j++) {
        if (posicionClave == clave.length()) {
          posicionClave = 0;
        }
        int posicion = caracteres.indexOf(Character.toString(i.charAt(j))) -
            caracteres.indexOf(Character.toString(clave.charAt(posicionClave))) - 1;
        nuevoTexto += obtenerCaracter(posicion);  
        posicionClave++;
      }
      nuevoTexto += " ";
    }
    return nuevoTexto;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    for (int i = 0; i < pTexto.length(); i++) {
      if(!Character.isAlphabetic(pTexto.charAt(i)) && pTexto.charAt(i) != ' ') {
        return true;
      }
    }
    return false;
  }

  private String obtenerCaracter(int pPosicion) {
    if (pPosicion > 25) {
      pPosicion -= 26;
    } else if (pPosicion < 0) {
      pPosicion += 26;
    }
    return Character.toString(caracteres.charAt(pPosicion));
  }
}
