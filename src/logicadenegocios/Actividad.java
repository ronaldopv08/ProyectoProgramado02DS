package logicadenegocios;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Clase simple para representar el registro de las interacciones de un usuario con la soluci�n
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class Actividad {
  
  public String fecha;
  public String hora;
  public String tipoCifrado;
  public String accion;
  
  /**
   * M�todo constructor de la clase, sin par�metros
   */
  public Actividad() {
    
  }
  
  /**
   * M�todo constructor de la clase
   * @param fecha Fecha de la actividad, formato String
   * @param hora Hora de la actividad, formato String
   * @param tipoCifrado M�todo de cifrado empleado, formato String
   * @param accion Acci�n criptrogr�fica realizada, formato String
   */
  public Actividad(String fecha, String hora, String tipoCifrado, String accion) {
    this.fecha = fecha;
    this.hora = hora;
    this.tipoCifrado = tipoCifrado;
    this.accion = accion;
  }

  /**
   * M�todo constructor de la clase
   * @param tipoCifrado M�todo de cifrado empleado, formato String
   * @param accion Acci�n criptrogr�fica realizada, formato String
   */
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

  /**
   * M�todo para obtener la informaci�n de un objeto de tipo Actividad, en formato String
   * @return String con la informaci�n de una instanciaci�n de la clase Actividad
   */
  @Override
  public String toString() {
    return "Actividad [fecha=" + fecha + ", hora=" + hora + ", tipoCifrado=" + tipoCifrado
        + ", accion=" + accion + "]";
  }

  
}
