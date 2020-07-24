package logicaderegistro;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import logicadenegocios.Actividad;

public class BitacoraCSV extends Bitacora {

  public BitacoraCSV() {
    this.rutaArchivo = new File("src/bitacoras/bitacora.csv").getAbsolutePath();
  }


  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      CSVReader csvReader = new CSVReader(new FileReader(rutaArchivo.toString()));
      List<String[]> datos = csvReader.readAll();
      String[] actividad = {pActividad.getFecha(), pActividad.getHora(),
          pActividad.getTipoCifrado(), pActividad.getAccion()};
      datos.add(actividad);
      CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo.toString()));
      writer.writeAll(datos);
      writer.close();
    } catch (IOException | CsvException e) {
      e.printStackTrace();
    }
  }

  @Override
  protected String consularTodosRegistros() {
    // TODO Auto-generated method stub
    return null;
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
