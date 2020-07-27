package logicaderegistro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import logicadeinstanciacion.ServicioAlmacenamientoRemotoSingleton;
import logicadenegocios.Actividad;

/**
 * Clase para acceder al archivo de la bitácora en formato de trama plana
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class BitacoraTramaPlana extends Bitacora {

  /**
   * Método constructor de la clase
   */
  public BitacoraTramaPlana() {
    servicio = ServicioAlmacenamientoRemotoSingleton.getInstance();
    this.rutaArchivo = servicio.descargarArchivo("bitacora.txt").getAbsolutePath();
  }

  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      File file = new File(rutaArchivo);
      Writer output = new BufferedWriter(new FileWriter(file, true));
      String nuevaActividad = "\n" + pActividad.getFecha() + "\t" + pActividad.hora + "\t"
          + pActividad.getTipoCifrado() + "\t" + pActividad.getAccion();
      output.write(nuevaActividad);
      output.close();
      servicio.subirArchivo(file);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  protected String consultarTodosRegistros() {
    String consulta = "";
    List<String> datos = consultarRegistros();
    consulta = aplicarFormatoHTML(datos);
    return consulta;
  }

  @Override
  protected String consultarCodificaciones() {
    String consulta = "";
    List<String> datos = consultarRegistros();
    List<String> datosFiltrados = new ArrayList<String>();
    for(String i: datos) {
      if(i.split("\t")[3].equals("codificacion")) {
        datosFiltrados.add(i);
      }
    }
    consulta = aplicarFormatoHTML(datosFiltrados);
    return consulta;
  }

  @Override
  protected String consultarDecodificaciones() {
    String consulta = "";
    List<String> datos = consultarRegistros();
    List<String> datosFiltrados = new ArrayList<String>();
    for(String i: datos) {
      if(i.contains("decodificacion")) {
        datosFiltrados.add(i);
      }
    }
    consulta = aplicarFormatoHTML(datosFiltrados);
    return consulta;
  }

  @Override
  protected String consultarAccionesHoy() {
    String consulta = "";
    List<String> datos = consultarRegistros();
    List<String> datosFiltrados = new ArrayList<String>();
    for(String i: datos) {
      if(validarFecha(i)) {
        datosFiltrados.add(i);
      }
    }
    consulta = aplicarFormatoHTML(datosFiltrados);
    return consulta;
  }

  private String aplicarFormatoHTML(List<String> pDatos) {
    String consulta = "";
    for (String i : pDatos) {
      consulta += i + "<br><br/>";
    }
    return consulta;
  }

  private List<String> consultarRegistros() {
    try {
      File file = new File(rutaArchivo);
      Scanner myReader = new Scanner(file);
      List<String> datos = new ArrayList<String>();
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        datos.add(data);
      }
      myReader.close();
      return datos;
    } catch (Exception e) {
      return null;
    }
  }
  
  private boolean validarFecha(String pFecha) {
    Date date = new Date();
    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    fecha.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    return pFecha.contains(fecha.format(date));
  }

}
