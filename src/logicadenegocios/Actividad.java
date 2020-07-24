package logicadenegocios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Actividad {
  
  private String fecha;
  private String hora;
  private String tipoCifrado;
  private String accion;
  
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

  public String getHora() {
    return hora;
  }

  public void setHora() {
    Date date = new Date();
    SimpleDateFormat hora = new SimpleDateFormat("HH:mm:ss");
    hora.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    this.hora = hora.format(date);
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

}
