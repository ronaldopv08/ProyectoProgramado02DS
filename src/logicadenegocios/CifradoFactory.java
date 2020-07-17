package logicadenegocios;

/**
 * Clase simple diseñada para instanciar objetos de tipo Cifrado
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public class CifradoFactory {
  
  /**
   * Método constructor de la clase, sin parámetros
   */
  public CifradoFactory() {}
  
  /**
   * Método que se encarga de instanciar objetos de tipo Cifrado
   * 
   * @param tipo String que representa el tipo de la clase Cifrado
   * @return Cifrado
   */
  public Cifrado crearCifrado(String tipo) throws InstantiationException, IllegalAccessException, 
    ClassNotFoundException {
    return (Cifrado) Class.forName("logicadenegocios."+tipo).newInstance();
  }
  
}
