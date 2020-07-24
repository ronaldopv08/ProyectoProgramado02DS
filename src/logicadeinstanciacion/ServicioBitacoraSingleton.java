package logicadeinstanciacion;

import logicadeservicios.ServicioBitacora;

/**
 * Clase encargada de encapsular la l�gica de instanciaci�n de la clase ServicioTraductor.
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioBitacoraSingleton {
  
  private static ServicioBitacora instance = new ServicioBitacora();
  
  private ServicioBitacoraSingleton() {
  }
 
 /**
  * M�todo para obtener la instancia de la clase ServicioTraductor
  * @return Objeto de tipo ServicioTraductor
  */
  public static ServicioBitacora getInstance() {
    return instance;
  }
 

}
