package logicadeinstanciacion;

import logicadeservicios.ServicioAlmacenamientoRemoto;
import logicadeservicios.ServicioWatson;

/**
 * Clase encargada de encapsular la lógica de instanciación de la clase ServicioAlmacenamientoRemoto.
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioAlmacenamientoRemotoSingleton {
  private static ServicioAlmacenamientoRemoto instance = new ServicioAlmacenamientoRemoto();
 
  private ServicioAlmacenamientoRemotoSingleton() {
  }
 
 /**
  * Método para obtener la instancia de la clase ServicioWatson
  * @return Objeto de tipo ServicioWatson.
  */
  public static ServicioAlmacenamientoRemoto getInstance() {
    return instance;
  }

}
