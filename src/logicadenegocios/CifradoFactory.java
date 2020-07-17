package logicadenegocios;

/**
 * Clase simple dise�ada para instanciar objetos de tipo Cifrado
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public class CifradoFactory {
  
  /**
   * M�todo constructor de la clase, sin par�metros
   */
  public CifradoFactory() {}
  
  /**
   * M�todo que se encarga de instanciar objetos de tipo Cifrado
   * 
   * @param tipo String que representa el tipo de la clase Cifrado
   * @return Cifrado
   */
  public Cifrado crearCifrado(String tipo) throws InstantiationException, IllegalAccessException, 
    ClassNotFoundException {
    return (Cifrado) Class.forName("logicadenegocios."+tipo).newInstance();
  }
  
}
