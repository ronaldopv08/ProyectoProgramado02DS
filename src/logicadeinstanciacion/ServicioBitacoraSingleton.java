package logicadeinstanciacion;

import logicadeservicios.ServicioBitacora;

/**
 * Clase encargada de encapsular la lógica de instanciación de la clase ServicioTraductor.
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioBitacoraSingleton {
  
  private static ServicioBitacora instance = new ServicioBitacora();
  
  private ServicioBitacoraSingleton() {
  }
 
 /**
  * Método para obtener la instancia de la clase ServicioTraductor
  * @return Objeto de tipo ServicioTraductor
  */
  public static ServicioBitacora getInstance() {
    return instance;
  }
 

}
