package logicadeinstanciacion;

import logicaderegistro.Bitacora;

public class BitacoraFactory {
  
  /**
   * Método constructor de la clase, sin parámetros
   */
  public BitacoraFactory() {}
  
  /**
   * Método que se encarga de instanciar objetos de tipo Bitacora
   * 
   * @param tipo String que representa el tipo de la clase Bitacora
   * @return Cifrado
   */
  public Bitacora crearBitacora(String tipo) throws InstantiationException, IllegalAccessException, 
    ClassNotFoundException {
    return (Bitacora) Class.forName("logicaderegistro."+tipo).newInstance();
  }

}
