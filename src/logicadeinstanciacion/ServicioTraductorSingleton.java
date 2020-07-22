package logicadeinstanciacion;

import logicadeservicios.ServicioTraductor;

/**
 * Clase encargada de encapsular la lógica de instanciación de la clase ServicioTraductor.
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioTraductorSingleton {
  private static ServicioTraductor instance = new ServicioTraductor();
  
  private ServicioTraductorSingleton() {
  }
 
 /**
  * Método para obtener la instancia de la clase ServicioTraductor
  * @return Objeto de tipo ServicioTraductor
  */
  public static ServicioTraductor getInstance() {
    return instance;
  }
 
}
