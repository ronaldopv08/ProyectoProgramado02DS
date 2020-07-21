package logicadeservicios;

/**
 * Clase encargada de encapsular la l�gica de instanciaci�n de la clase ServioAnalizadorTono.
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioAnalizadorTonoSingleton {
  
  private static ServicioAnalizadorTono instance = new ServicioAnalizadorTono();
 
  private ServicioAnalizadorTonoSingleton() {
  }
 
 /**
  * M�todo para obtener la instancia de la clase ServicioAnalizadorTono
  * @return Objeto de tipo ServicioAnalizadorTono.
  */
  public static ServicioAnalizadorTono getInstance() {
    return instance;
  }
 
}
