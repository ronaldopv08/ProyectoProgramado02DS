package logicaderegistro;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import logicadeinstanciacion.ServicioAlmacenamientoRemotoSingleton;
import logicadenegocios.Actividad;

public class BitacoraCSV extends Bitacora {

  public BitacoraCSV() {
    servicio = ServicioAlmacenamientoRemotoSingleton.getInstance();
    this.rutaArchivo = servicio.descargarArchivo("bitacora.csv").getAbsolutePath();
  }


  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      CSVReader csvReader = new CSVReader(new FileReader(rutaArchivo));
      List<String[]> datos = csvReader.readAll();
      String[] actividad = {pActividad.getFecha(), pActividad.getHora(),
          pActividad.getTipoCifrado(), pActividad.getAccion()};
      datos.add(actividad);
      CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo));
      writer.writeAll(datos);
      writer.close();
      csvReader.close();
      File archivo = new File(rutaArchivo);
      servicio.subirArchivo(archivo);
    } catch (IOException | CsvException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String consultarTodosRegistros() {
    String consulta = "";
    List<String[]> datos = consultarRegistros();
    consulta = aplicarFormatoHTML(datos);
    return consulta;
  }

  @Override
  protected String consultarCodificaciones() {
    String consulta = "";
    List<String[]> datosFiltrados = new ArrayList<String[]>();
    List<String[]> datos = consultarRegistros();
    for (String[] i: datos) {
      if(i[3].equals("codificacion")) {
        datosFiltrados.add(i);
      }
    }
    consulta = aplicarFormatoHTML(datosFiltrados);
    return consulta;
  }

  @Override
  protected String consultarDecodificaciones() {
    String consulta = "";
    List<String[]> datosFiltrados = new ArrayList<String[]>();
    List<String[]> datos = consultarRegistros();
    for (String[] i: datos) {
      if(i[3].equals("decodificacion")) {
        datosFiltrados.add(i);
      }
    }
    consulta = aplicarFormatoHTML(datosFiltrados);
    return consulta;
  }

  @Override
  protected String consultarAccionesHoy() {
    String consulta = "";
    List<String[]> datosFiltrados = new ArrayList<String[]>();
    List<String[]> datos = consultarRegistros();
    for (String[] i: datos) {
      if(validarFecha(i[0])) {
        datosFiltrados.add(i);
      }
    }
    consulta = aplicarFormatoHTML(datosFiltrados);
    return consulta;
  }
  
  private String aplicarFormatoHTML(List<String[]> pDatos) {
    String consulta = "";
    for (String[] i : pDatos) {
      for(String j: i) {
        consulta += j +",";
      }
       consulta = consulta.substring(0, consulta.length() - 1) + "<br><br/>";
    }
    return consulta;
  }
  
  private List<String[]> consultarRegistros(){
      try {
        CSVReader csvReader = new CSVReader(new FileReader(rutaArchivo));
        List<String[]> datos = csvReader.readAll();
        return datos;
      } catch (Exception e) {
        return null;
      }
  }
  
  private boolean validarFecha(String pFecha) {
    Date date = new Date();
    SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-yyyy");
    fecha.setTimeZone(TimeZone.getTimeZone("America/Costa_Rica"));
    return fecha.format(date).equals(pFecha);
  }

}
