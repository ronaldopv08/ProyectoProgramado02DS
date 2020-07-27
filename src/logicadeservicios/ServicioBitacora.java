package logicadeservicios;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import logicadeinstanciacion.BitacoraFactory;
import logicadenegocios.Actividad;
import logicaderegistro.Bitacora;

/**
 * Clase para acceder a los servicios de registro de actividades del sistema
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioBitacora {
  
  private List<Bitacora> bitacoras;
  private BitacoraFactory bitacoraFactory;
  
  /**
   * Método constructor de la clase
   */
  public ServicioBitacora() {
    bitacoraFactory = new BitacoraFactory();
    bitacoras = new ArrayList<Bitacora>();
    agregarBitacora("BitacoraCSV");
    agregarBitacora("BitacoraTramaPlana");
    agregarBitacora("BitacoraXML");
  }
  
  /**
   * Método para registrar una actividad en las diferentes bitacoras disponibles
   * @param pDatos colección de Strings con información de un objeto de tipo Actividad
   */
  public void registarActividad(List<String> pDatos) {
    Actividad nuevaActividad = new Actividad(pDatos.get(1), pDatos.get(2));
    for (Bitacora i: bitacoras) {
      i.registrarActividad(nuevaActividad);
    }
  }
  
  /**
   * Método para obtener la información almacenada en un tipo de bitácora, aplicando
   * un filtro de consulta proporcionado
   * @param tipoBitacora Nombre del tipo de bitacora por consultar, formato String
   * @param pFiltroConsulta Nombre del método que ejecuta la consulta, formato String
   * @return String con la información obtenida
   * @throws NoSuchMethodException
   * @throws SecurityException
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   */
  public String consultarRegistros(String tipoBitacora, String pFiltroConsulta) 
      throws NoSuchMethodException, SecurityException, IllegalAccessException, 
      IllegalArgumentException, InvocationTargetException {
    for (Bitacora i: bitacoras) {
      if(i.getClass().getSimpleName().equals(tipoBitacora)) {
        return i.ejecutarConsulta(pFiltroConsulta);
      }
    }
    return null;
  }
  
  private void agregarBitacora(String pTipoBitacora) {
    try {
      bitacoras.add(bitacoraFactory.crearBitacora(pTipoBitacora));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
