package logicadeinstanciacion;

import logicadeservicios.ServicioWatson;

/**
 * Clase encargada de encapsular la lógica de instanciación de la clase ServicioWatson.
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioWatsonSingleton {
  private static ServicioWatson instance = new ServicioWatson();
 
  private ServicioWatsonSingleton() {
  }
 
 /**
  * Método para obtener la instancia de la clase ServicioWatson
  * @return Objeto de tipo ServicioWatson.
  */
  public static ServicioWatson getInstance() {
    return instance;
  }

}
  