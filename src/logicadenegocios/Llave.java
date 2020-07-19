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
    if(validarMensaje(pTexto)){
      return "N/A";
    }
    String nuevoTexto = "";
    for(String i : pTexto.split(" ")) {
      nuevoTexto += sustituirPalabra(i, 1) +" ";
    }
    return nuevoTexto;
  }

  @Override
  public String descifrar(String pTexto) {
    if(validarMensaje(pTexto)){
      return "N/A";
    }
    String nuevoTexto = "";
    for(String i : pTexto.split(" ")) {
      nuevoTexto += sustituirPalabra(i, -1) +" ";
    }
    return nuevoTexto;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    if(pTexto.isEmpty()) {
      return true;
    }
    return !pTexto.matches("[a-z\\ ]*");
  }
  
  private String sustituirPalabra(String pTexto, int pPosicion) {
    int posicionClave = 0;
    String nuevoTexto = "";
    for(int j=0; j<pTexto.length();j++) {
      if (posicionClave == clave.length()) {
        posicionClave = 0;
      }
      int posicion = pPosicion*(caracteres.indexOf(Character.toString(clave.charAt(posicionClave)))) + 
          caracteres.indexOf(Character.toString(pTexto.charAt(j))) + pPosicion;
      nuevoTexto += obtenerCaracter(posicion); 
      posicionClave++;
    }
    return nuevoTexto;
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
