package logicaderegistro;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import logicadeinstanciacion.ServicioAlmacenamientoRemotoSingleton;
import logicadenegocios.Actividad;
import logicadeservicios.ServicioAlmacenamientoRemoto;

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
    try {
      CSVReader csvReader = new CSVReader(new FileReader(rutaArchivo));
      List<String[]> datos = csvReader.readAll();
      for (String[] i : datos) {
        for(String j: i) {
          consulta += j +",";
        }
         consulta = consulta.substring(0, consulta.length() - 1) + "<br><br/>";
      }
      return consulta;
    } catch (IOException | CsvException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return consulta;
    }
  }

  @Override
  protected String consultarCodifcaciones() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String consultarDecodificaciones() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String consultarAccionesHoy() {
    // TODO Auto-generated method stub
    return null;
  }

}
