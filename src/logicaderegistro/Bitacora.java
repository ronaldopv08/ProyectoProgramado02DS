package logicaderegistro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public abstract class Bitacora {
  
  protected String rutaArchivo;
  
  public abstract void registrarActividad(String tipoActividad);
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
  
  protected String obtenerHora() {
    Date date = new Date();
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    hora.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    fecha.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    return hora.format(date);
  }
  
  protected String obtenerFecha() {
    Date date = new Date();
    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    fecha.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    return fecha.format(date);
  }  
  
}
