package logicadenegocios;

/**
 * Clase simple para cifrar utilizando el algoritmo de mensaje inverso
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class MensajeInverso extends Cifrado {

  /**
   * M�todo constructor de la clase, no recibe par�metros.
   */
  public MensajeInverso() {
    
  }
  
  @Override
  public String cifrar(String pTexto) {
    if (validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoCifrado = "";
    for (int i=0;i<pTexto.length();i++) {
      textoCifrado += pTexto.charAt(pTexto.length()-1-i);
    }
    return textoCifrado;
  }

  @Override
  public String descifrar(String pTexto) {
    if (validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoCifrado = "";
    for (int i=0;i<pTexto.length();i++) {
      textoCifrado += pTexto.charAt(pTexto.length()-1-i);
    }
    return textoCifrado;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    return pTexto.isEmpty();
  }

}
