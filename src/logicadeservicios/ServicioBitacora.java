package logicadeservicios;

import java.util.ArrayList;
import java.util.List;
import logicadeinstanciacion.BitacoraFactory;
import logicaderegistro.Bitacora;

public class ServicioBitacora {
  
  private List<Bitacora> bitacoras;
  private BitacoraFactory bitacoraFactory;
  
  public ServicioBitacora() {
    bitacoras = new ArrayList<Bitacora>();
  }
  
  public void agregarBitacoras(String pTipoBitacora) {
    try {
      bitacoras.add(bitacoraFactory.crearBitacora(pTipoBitacora));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public void registarActividad(String pActividad) {
    for (Bitacora i: bitacoras) {
      i.registrarActividad(pActividad);
    }
  }
  
  public String consultarRegistros(String tipoBitacora, String pFiltroConsulta) {
    for (Bitacora i: bitacoras) {
      if(i.getClass().getName().equals(tipoBitacora)) {
        return i.ejecutarConsulta(pFiltroConsulta);
      }
    }
    return "";
  }

}
