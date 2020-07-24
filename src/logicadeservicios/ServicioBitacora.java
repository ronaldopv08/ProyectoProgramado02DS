package logicadeservicios;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import logicadeinstanciacion.BitacoraFactory;
import logicadenegocios.Actividad;
import logicaderegistro.Bitacora;

public class ServicioBitacora {
  
  private List<Bitacora> bitacoras;
  private BitacoraFactory bitacoraFactory;
  
  public ServicioBitacora() {
    bitacoraFactory = new BitacoraFactory();
    bitacoras = new ArrayList<Bitacora>();
    agregarBitacora("BitacoraCSV");
    //agregarBitacora("BitacoraTramaPlana");
    agregarBitacora("BitacoraXML");
  }
  
  public void registarActividad(List<String> pDatos) {
    Actividad nuevaActividad = new Actividad(pDatos.get(1), pDatos.get(2));
    for (Bitacora i: bitacoras) {
      i.registrarActividad(nuevaActividad);
    }
  }
  
  public Object consultarRegistros(String tipoBitacora, String pFiltroConsulta) 
      throws NoSuchMethodException, SecurityException, IllegalAccessException, 
      IllegalArgumentException, InvocationTargetException {
    for (Bitacora i: bitacoras) {
      if(i.getClass().getName().equals(tipoBitacora)) {
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
