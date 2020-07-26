package logicaderegistro;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import logicadeinstanciacion.ServicioAlmacenamientoRemotoSingleton;
import logicadenegocios.Actividad;
import logicadenegocios.ListaActividadesXML;

public class BitacoraXML extends Bitacora {
  
  //@JacksonXmlElementWrapper(useWrapping = false)
  private ListaActividadesXML listaActividades;
  
  public BitacoraXML() {
    servicio = ServicioAlmacenamientoRemotoSingleton.getInstance();
    listaActividades = new ListaActividadesXML();
    this.rutaArchivo = servicio.descargarArchivo("bitacora.xml").getAbsolutePath();
  }
  
  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      File archivoXML = new File(rutaArchivo);
      ObjectMapper mapper = new XmlMapper();
      InputStream inputStream = new FileInputStream(archivoXML);
      TypeReference<ListaActividadesXML> typeReference = new TypeReference<ListaActividadesXML>() {};
      listaActividades = mapper.readValue(inputStream, typeReference);
      listaActividades.agregarActividad(pActividad);
      mapper.writeValue(archivoXML, listaActividades);
      inputStream.close();
      servicio.subirArchivo(archivoXML);
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Override
  protected String consultarTodosRegistros() {
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
