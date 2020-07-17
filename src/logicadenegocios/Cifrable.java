package logicadenegocios;

/**
 * Clase interface para un Cifrable
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public interface Cifrable {
  
  /**
   * Metodo para cifrar un texto
   * 
   * @param pTexto String que representa el texto que ser� cifrado
   * @return String del texto cifrado
   */
  public abstract String cifrar(String pTexto);
  
  /**
   * Metodo para descifrar un texto
   * 
   * @param pTexto String que representa el texto que ser� descifrado
   * @return String del texto descifrado
   */
  public abstract String descifrar(String pTexto);
  
}
