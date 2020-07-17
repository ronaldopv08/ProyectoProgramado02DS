package logicadenegocios;

/**
 * Clase abstracta para representar la jerarqu�a de clases para los algoritmos de cifrado por sustituci�n
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public abstract class Sustitucion extends Cifrado {
  
  protected String caracteres;
  protected String clave;
  
  /**
   * M�todo constructor de la clase, no recibe par�metros.
   */
  public Sustitucion() {
    caracteres = "abcdefghijklmnopqrstuvwxyz";
  }
  
  void setClave(String pClave) {
    clave = pClave;
  }

}
