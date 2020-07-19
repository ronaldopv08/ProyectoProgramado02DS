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
      textoCifrado+=sustituirPalabra(palabra,Double.valueOf(clave)/10,Double.valueOf(clave)%10) +
          " ";
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
      textoDescifrado+=sustituirPalabra(palabra,27-Double.valueOf(clave)/10,
          26-Double.valueOf(clave)%10) + " ";
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
  
  private String sustituirPalabra(String pTexto, double primerDesplazamiento,
      double segundoDesplazamiento){
    String nuevoTexto = "";
    for (int i=0;i<pTexto.length();i+=2) {
      if(i+1>pTexto.length()) {
        nuevoTexto += desplazarLetra(pTexto.charAt(i), primerDesplazamiento);
        break;
      }
      nuevoTexto += desplazarLetra(pTexto.charAt(i), primerDesplazamiento);
      nuevoTexto += desplazarLetra(pTexto.charAt(i+1), segundoDesplazamiento);
    }
    return nuevoTexto;
  }
  
  private String desplazarLetra(char pCaracter,double desplazamiento) {
    int posicion = (int) (calcularDesplazamiento(caracteres.indexOf(pCaracter),
        desplazamiento));
    return String.valueOf(caracteres.charAt(posicion));
  }
  
  private double calcularDesplazamiento(int pPosicion,double pDesplazamiento) {
    return (pPosicion+pDesplazamiento)%26;
  }

}
