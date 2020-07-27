package logicadeservicios;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import logicadeinstanciacion.BitacoraFactory;
import logicadenegocios.Actividad;
import logicaderegistro.Bitacora;

/**
 * Clase para acceder a los servicios de registro de actividades del sistema
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioBitacora {
  
  private List<Bitacora> bitacoras;
  private BitacoraFactory bitacoraFactory;
  
  /**
   * M�todo constructor de la clase
   */
  public ServicioBitacora() {
    bitacoraFactory = new BitacoraFactory();
    bitacoras = new ArrayList<Bitacora>();
    agregarBitacora("BitacoraCSV");
    agregarBitacora("BitacoraTramaPlana");
    agregarBitacora("BitacoraXML");
  }
  
  /**
   * M�todo para registrar una actividad en las diferentes bitacoras disponibles
   * @param pDatos colecci�n de Strings con informaci�n de un objeto de tipo Actividad
   */
  public void registarActividad(List<String> pDatos) {
    Actividad nuevaActividad = new Actividad(pDatos.get(1), pDatos.get(2));
    for (Bitacora i: bitacoras) {
      i.registrarActividad(nuevaActividad);
    }
  }
  
  /**
   * M�todo para obtener la informaci�n almacenada en un tipo de bit�cora, aplicando
   * un filtro de consulta proporcionado
   * @param tipoBitacora Nombre del tipo de bitacora por consultar, formato String
   * @param pFiltroConsulta Nombre del m�todo que ejecuta la consulta, formato String
   * @return String con la informaci�n obtenida
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
