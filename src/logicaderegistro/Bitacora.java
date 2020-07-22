package logicaderegistro;

public abstract class Bitacora {
  
  protected String rutaArchivo;
  
  public abstract void registrarActividad(String tipoActividad);
  protected abstract String consularTodosRegistros();
  protected abstract String consultarCodifcaciones();
  protected abstract String consultarDecodificaciones();
  protected abstract String consultarAccionesHoy();
  
  public String ejecutarConsulta(String filtroConsulta) {
    return "";
  }
  
  
}
