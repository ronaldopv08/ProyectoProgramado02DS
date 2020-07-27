package logicaderegistro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import logicadenegocios.Actividad;
import logicadeservicios.ServicioAlmacenamientoRemoto;

/**
 * Clase abstracta para representar la capa de abstracci�n entre el servicio de bit�coras
 * y los diferentes tipos de bit�coras
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public abstract class Bitacora {
  
  protected String rutaArchivo;
  protected ServicioAlmacenamientoRemoto servicio;
  
  /**
   * M�todo constructor de la clase
   */
  public Bitacora() {
    
  }
  
  /**
   * M�todo para registrar una actividad en el archivo de almacenamiento de la bit�cora
   * @param pActividad Objeto de tipo Actividad
   */
  public abstract void registrarActividad(Actividad pActividad);
  
  /**
   * M�todo para consultar todos los registros almacenados
   * @return String con la informaci�n obtenida.
   */
  protected abstract String consultarTodosRegistros();
  /**
   * M�todo para consultar todas las codificaciones registradas
   * @return String con la informaci�n obtenida
   */
  protected abstract String consultarCodificaciones();
  /**
   * M�todo para consultar todas las decodificaciones registradas
   * @return String con la informaci�n obtenida
   */
  protected abstract String consultarDecodificaciones();
  /**
   * M�todo para consultar todos los registros del d�a
   * @return
   */
  protected abstract String consultarAccionesHoy();
  
  /**
   * M�todo para ejecutar una consulta de los registros almacenados
   * @param filtroConsulta Nombre del m�todo de consulta, formato String
   * @return String con la informaci�n obtenida
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
