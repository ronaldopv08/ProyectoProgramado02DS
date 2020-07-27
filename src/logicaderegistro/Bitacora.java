package logicaderegistro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import logicadenegocios.Actividad;
import logicadeservicios.ServicioAlmacenamientoRemoto;

/**
 * Clase abstracta para representar la capa de abstracción entre el servicio de bitácoras
 * y los diferentes tipos de bitácoras
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public abstract class Bitacora {
  
  protected String rutaArchivo;
  protected ServicioAlmacenamientoRemoto servicio;
  
  /**
   * Método constructor de la clase
   */
  public Bitacora() {
    
  }
  
  /**
   * Método para registrar una actividad en el archivo de almacenamiento de la bitácora
   * @param pActividad Objeto de tipo Actividad
   */
  public abstract void registrarActividad(Actividad pActividad);
  
  /**
   * Método para consultar todos los registros almacenados
   * @return String con la información obtenida.
   */
  protected abstract String consultarTodosRegistros();
  /**
   * Método para consultar todas las codificaciones registradas
   * @return String con la información obtenida
   */
  protected abstract String consultarCodificaciones();
  /**
   * Método para consultar todas las decodificaciones registradas
   * @return String con la información obtenida
   */
  protected abstract String consultarDecodificaciones();
  /**
   * Método para consultar todos los registros del día
   * @return
   */
  protected abstract String consultarAccionesHoy();
  
  /**
   * Método para ejecutar una consulta de los registros almacenados
   * @param filtroConsulta Nombre del método de consulta, formato String
   * @return String con la información obtenida
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   */
  public String ejecutarConsulta(String filtroConsulta) throws 
    NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    Method metodo = this.getClass().getDeclaredMethod(filtroConsulta);
    metodo.setAccessible(true);
    String resultado = (String) metodo.invoke(this);
    return resultado;
  }
  
}
