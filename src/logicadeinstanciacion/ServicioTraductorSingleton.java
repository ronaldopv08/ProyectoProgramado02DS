package logicadeinstanciacion;

import logicadeservicios.ServicioTraductor;

/**
 * Clase encargada de encapsular la l�gica de instanciaci�n de la clase ServicioTraductor.
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioTraductorSingleton {
  private static ServicioTraductor instance = new ServicioTraductor();
  
  private ServicioTraductorSingleton() {
  }
 
 /**
  * M�todo para obtener la instancia de la clase ServicioTraductor
  * @return Objeto de tipo ServicioTraductor
  */
  public static ServicioTraductor getInstance() {
    return instance;
  }
 
}
