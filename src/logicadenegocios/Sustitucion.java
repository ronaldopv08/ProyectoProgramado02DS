package logicadenegocios;

/**
 * Clase abstracta para representar la jerarquía de clases para los algoritmos de cifrado por sustitución
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public abstract class Sustitucion extends Cifrado {
  
  protected String caracteres;
  protected String clave;
  
  /**
   * Método constructor de la clase, no recibe parámetros.
   */
  public Sustitucion() {
    caracteres = "abcdefghijklmnopqrstuvwxyz";
  }
  
  void setClave(String pClave) {
    clave = pClave;
  }

}
