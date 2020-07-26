package logicaderegistro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import logicadenegocios.Actividad;
import logicadeservicios.ServicioAlmacenamientoRemoto;

public abstract class Bitacora {
  
  protected String rutaArchivo;
  protected ServicioAlmacenamientoRemoto servicio;
  
  public abstract void registrarActividad(Actividad pActividad);
  protected abstract String consultarTodosRegistros();
  protected abstract String consultarCodificaciones();
  protected abstract String consultarDecodificaciones();
  protected abstract String consultarAccionesHoy();
  
  public String ejecutarConsulta(String filtroConsulta) throws 
    NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    Method metodo = this.getClass().getDeclaredMethod(filtroConsulta);
    metodo.setAccessible(true);
    String resultado = (String) metodo.invoke(this);
    return resultado;
  }
  
}
