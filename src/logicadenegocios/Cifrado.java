package logicadenegocios;

/**
 * Clase abstracta para un Cifrado
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public abstract class Cifrado implements Cifrable {
  
  /**
   * M�todo constructor de la clase, sin par�metros
   */  
  public Cifrado(){}
  
  /**
   * M�todo que valida si el texto puede ser cifrado o descifrado por la clase
   * 
   * @param pTexto String que representa el texto que ser� validado
   * @return boolean que representa si puede ser cifrado o no
   */
  protected abstract boolean validarMensaje(String pTexto);
  
}
