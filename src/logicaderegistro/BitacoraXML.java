package logicaderegistro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import logicadenegocios.Actividad;

public class BitacoraXML extends Bitacora {
  
  
  public BitacoraXML() {
    this.rutaArchivo = new File("src/bitacoras/bitacora.xml").getAbsolutePath();
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



  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      File archivoXML = new File(rutaArchivo);
      ObjectMapper mapper = new XmlMapper();
      InputStream inputStream = new FileInputStream(archivoXML);
      TypeReference<List<Actividad>> typeReference = new TypeReference<List<Actividad>>() {};
      List<Actividad> actividades = mapper.readValue(inputStream, typeReference);
     // for(Actividad p :persons) {
       //   System.out.println(p.toString());
      //}
      actividades.add(pActividad);
      mapper.writeValue(archivoXML, actividades);
      inputStream.close();
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
  }


}
