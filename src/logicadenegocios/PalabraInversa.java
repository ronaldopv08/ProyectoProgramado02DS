package logicadenegocios;

/**
 * Clase simple para cifrar por Palabra Inversa
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public class PalabraInversa extends Cifrado {
   
  /**
   * Método constructor de la clase, sin parámetros
   */
  public PalabraInversa() {}

  @Override
  public String cifrar(String pTexto) {
    if(validarMensaje(pTexto)) {
      return "N/A";
    }
    return invertirTexto(pTexto);
  }

  @Override
  public String descifrar(String pTexto) {
    if(validarMensaje(pTexto)) {
      return "N/A";
    }
    return invertirTexto(pTexto);
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    return pTexto.isEmpty();
  }
  
  private String invertirTexto(String pTexto) {
    String textoInvertido = "";
    String palabra = "";
    for(int i=0; i<pTexto.length(); i++) {
      palabra += pTexto.charAt(i);
      if( (pTexto.charAt(i) == " ".charAt(0)) || i==pTexto.length()-1 ) {
        textoInvertido += invertirPalabra(palabra).replace(" ", "") + " ";
        palabra = "";
      }
    }
    return textoInvertido;
  }
  
  private String invertirPalabra(String pPalabra) {
    String palabraInvertida = "";
    for (int x=pPalabra.length()-1;x>=0;x--) {
      palabraInvertida += pPalabra.charAt(x);
    }
    return palabraInvertida;
  }  
}
