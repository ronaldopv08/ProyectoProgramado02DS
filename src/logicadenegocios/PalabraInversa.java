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
  public PalabraInversa() {
    
  }

  @Override
  public String cifrar(String pTexto) {
    if(validarMensaje(pTexto)) {
      return "N/A";
    }
    String nuevaPalabra = "";
    for(String i : pTexto.split(" ")) {
      nuevaPalabra += invertirPalabra(i) + " ";
    }
    return nuevaPalabra;
  }

  @Override
  public String descifrar(String pTexto) {
    if(validarMensaje(pTexto)) {
      return "N/A";
    }
    String nuevaPalabra = "";
    for(String i : pTexto.split(" ")) {
      nuevaPalabra += invertirPalabra(i) + " ";
    }
    return nuevaPalabra;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    return pTexto.isEmpty();
  }
  
  private String invertirPalabra(String pPalabra) {
    String palabraInvertida = "";
    for (int x=pPalabra.length()-1;x>=0;x--) {
      palabraInvertida += pPalabra.charAt(x);
    }
    return palabraInvertida;
  }  
}
