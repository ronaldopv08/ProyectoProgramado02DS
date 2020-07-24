package logicaderegistro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import logicadenegocios.Actividad;

public abstract class Bitacora {
  
  protected String rutaArchivo;
  
  public abstract void registrarActividad(Actividad pActividad);
  protected abstract String consularTodosRegistros();
  protected abstract String consultarCodifcaciones();
  protected abstract String consultarDecodificaciones();
  protected abstract String consultarAccionesHoy();
  
  public Object ejecutarConsulta(String filtroConsulta) throws 
    NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
      InvocationTargetException {
    Method metodo = this.getClass().getMethod(filtroConsulta, new Class[0]);
    metodo.setAccessible(true);
    Object resultado = metodo.invoke(this, new Object[0]);
    return resultado;
  }
  
}
