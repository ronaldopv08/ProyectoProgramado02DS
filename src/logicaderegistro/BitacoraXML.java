package logicaderegistro;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import logicadenegocios.Actividad;

public class BitacoraXML extends Bitacora {
  
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Actividad> actividades;  
  
  public BitacoraXML() {
    this.actividades = new ArrayList<Actividad>();
    this.rutaArchivo = new File("src/bitacoras/bitacora.xml").getAbsolutePath();
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
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  protected String consularTodosRegistros() {
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
