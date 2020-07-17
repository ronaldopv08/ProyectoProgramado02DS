package logicadenegocios;

/**
 * Clase interface para un Cifrable
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public interface Cifrable {
  
  /**
   * Metodo para cifrar un texto
   * 
   * @param pTexto String que representa el texto que será cifrado
   * @return String del texto cifrado
   */
  public abstract String cifrar(String pTexto);
  
  /**
   * Metodo para descifrar un texto
   * 
   * @param pTexto String que representa el texto que será descifrado
   * @return String del texto descifrado
   */
  public abstract String descifrar(String pTexto);
  
}
