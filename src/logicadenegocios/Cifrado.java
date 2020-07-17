package logicadenegocios;

/**
 * Clase abstracta para un Cifrado
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public abstract class Cifrado implements Cifrable {
  
  /**
   * Método constructor de la clase, sin parámetros
   */  
  public Cifrado(){}
  
  /**
   * Método que valida si el texto puede ser cifrado o descifrado por la clase
   * 
   * @param pTexto String que representa el texto que será validado
   * @return boolean que representa si puede ser cifrado o no
   */
  protected abstract boolean validarMensaje(String pTexto);
  
}
