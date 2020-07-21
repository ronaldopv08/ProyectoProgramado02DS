package logicadeservicios;

/**
 * Clase encargada de encapsular la lógica de instanciación de la clase ServioAnalizadorTono.
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioAnalizadorTonoSingleton {
  
  private static ServicioAnalizadorTono instance = new ServicioAnalizadorTono();
 
  private ServicioAnalizadorTonoSingleton() {
  }
 
 /**
  * Método para obtener la instancia de la clase ServicioAnalizadorTono
  * @return Objeto de tipo ServicioAnalizadorTono.
  */
  public static ServicioAnalizadorTono getInstance() {
    return instance;
  }
 
}
