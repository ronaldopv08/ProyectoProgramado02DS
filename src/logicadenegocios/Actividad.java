package logicadenegocios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Actividad {
  
  public String fecha;
  public String hora;
  public String tipoCifrado;
  public String accion;
  
  public Actividad() {
    
  }
  
  public Actividad(String fecha, String hora, String tipoCifrado, String accion) {
    this.fecha = fecha;
    this.hora = hora;
    this.tipoCifrado = tipoCifrado;
    this.accion = accion;
  }

  public Actividad(String pTipoCifrado, String pAccion) {
    setAccion(pAccion);
    setTipoCifrado(pTipoCifrado);
    setFecha();
    setHora();
  }

  public String getFecha() {
    return fecha;
  }
  
  public void setFecha() {
    Date date = new Date();
    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    fecha.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    this.fecha = fecha.format(date);
  }
  
  public void setFecha(String pFecha) {
    this.fecha = pFecha;
  }

  public String getHora() {
    return hora;
  }

  public void setHora() {
    Date date = new Date();
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    hora.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    this.hora = hora.format(date);
  }
  
  public void setHora(String pHora) {
    this.hora = pHora;
  }

  public String getTipoCifrado() {
    return tipoCifrado;
  }

  public void setTipoCifrado(String tipoCifrado) {
    this.tipoCifrado = tipoCifrado;
  }

  public String getAccion() {
    return accion;
  }

  public void setAccion(String accion) {
    this.accion = accion;
  }

  @Override
  public String toString() {
    return "Actividad [fecha=" + fecha + ", hora=" + hora + ", tipoCifrado=" + tipoCifrado
        + ", accion=" + accion + "]";
  }

  
}
