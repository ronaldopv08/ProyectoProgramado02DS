package logicadeinstanciacion;

import logicadeservicios.ServicioWatson;

/**
 * Clase encargada de encapsular la l�gica de instanciaci�n de la clase ServicioWatson.
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioWatsonSingleton {
  private static ServicioWatson instance = new ServicioWatson();
 
  private ServicioWatsonSingleton() {
  }
 
 /**
  * M�todo para obtener la instancia de la clase ServicioWatson
  * @return Objeto de tipo ServicioWatson.
  */
  public static ServicioWatson getInstance() {
    return instance;
  }

}
  