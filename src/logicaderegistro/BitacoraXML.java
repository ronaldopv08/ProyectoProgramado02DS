package logicaderegistro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import logicadeinstanciacion.ServicioAlmacenamientoRemotoSingleton;
import logicadenegocios.Actividad;
import logicadeservicios.ServicioAlmacenamientoRemoto;

public class BitacoraXML extends Bitacora {
  
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Actividad> actividades;
  
  public BitacoraXML() {
    servicio = ServicioAlmacenamientoRemotoSingleton.getInstance();
    this.actividades = new ArrayList<Actividad>();
    this.rutaArchivo = servicio.descargarArchivo("bitacora.xml").getAbsolutePath();
  }
  
  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      File archivoXML = new File(rutaArchivo);
      ObjectMapper mapper = new XmlMapper();
      InputStream inputStream = new FileInputStream(archivoXML);
      TypeReference<List<Actividad>> typeReference = new TypeReference<List<Actividad>>() {};
      actividades.clear();
      actividades = mapper.readValue(inputStream, typeReference);
      actividades.add(pActividad);
      mapper.writeValue(archivoXML, actividades);
      inputStream.close();
      servicio.subirArchivo(archivoXML);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  protected String consultarTodosRegistros() {
    String consulta = "";
    List<Actividad> datos = consultarRegistros();
    consulta = aplicarFormatoHTML(datos);
    return consulta;
  }

  @Override
  protected String consultarCodificaciones() {
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
  
  private String aplicarFormatoHTML(List<Actividad> pDatos) {
    String consulta = "";
    for (Actividad i : pDatos) {
      consulta += i + "<br><br/>";
    }
    return consulta;
  }

  private List<Actividad> consultarRegistros() {
    try {
      File archivoXML = new File(rutaArchivo);
      ObjectMapper mapper = new XmlMapper();
      InputStream inputStream = new FileInputStream(archivoXML);
      TypeReference<List<Actividad>> typeReference = new TypeReference<List<Actividad>>() {};
      actividades.clear();
      actividades = mapper.readValue(inputStream, typeReference);
      inputStream.close();
      return actividades;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
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
