package logicadeinstanciacion;

import logicadeservicios.ServicioAlmacenamientoRemoto;
import logicadeservicios.ServicioWatson;

/**
 * Clase encargada de encapsular la l�gica de instanciaci�n de la clase ServicioAlmacenamientoRemoto.
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioAlmacenamientoRemotoSingleton {
  private static ServicioAlmacenamientoRemoto instance = new ServicioAlmacenamientoRemoto();
 
  private ServicioAlmacenamientoRemotoSingleton() {
  }
 
 /**
  * M�todo para obtener la instancia de la clase ServicioWatson
  * @return Objeto de tipo ServicioWatson.
  */
  public static ServicioAlmacenamientoRemoto getInstance() {
    return instance;
  }

}
