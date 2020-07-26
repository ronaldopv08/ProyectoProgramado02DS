package logicaderegistro;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    String consulta = "";
    List<Actividad> datos = consultarRegistros();
    consulta = aplicarFormatoHTML(datos);
    return consulta;
  }

  @Override
  protected String consultarCodificaciones() {
    List<Actividad> datos = consultarRegistros();
    List<Actividad> datosFiltrados = new ArrayList<Actividad>();
    for (Actividad i : datos) {
      if (i.getAccion().equals("codificacion")) {
        datosFiltrados.add(i);
      }
    }
    return aplicarFormatoHTML(datosFiltrados);
  }

  @Override
  protected String consultarDecodificaciones() {
    List<Actividad> datos = consultarRegistros();
    List<Actividad> datosFiltrados = new ArrayList<Actividad>();
    for (Actividad i : datos) {
      if (i.getAccion().equals("decodificacion")) {
        datosFiltrados.add(i);
      }
    }
    return aplicarFormatoHTML(datosFiltrados);
  }

  @Override
  protected String consultarAccionesHoy() {
    List<Actividad> datos = consultarRegistros();
    List<Actividad> datosFiltrados = new ArrayList<Actividad>();
    for (Actividad i : datos) {
      if (validarFecha(i.getFecha())) {
        datosFiltrados.add(i);
      }
    }
    return aplicarFormatoHTML(datosFiltrados);
  }
  
  private String aplicarFormatoHTML(List<Actividad> pDatos) {
    String consulta = "<xmp>";
    consulta += "<ListaActividadesXML>\n\t<actividades>\n";
    for (Actividad i : pDatos) {
      consulta += "\t\t<actividades>\n";
      consulta += "\t\t\t<fecha>"+i.getFecha()+"</fecha>\n";
      consulta += "\t\t\t<hora>"+i.getHora()+"</hora>\n";
      consulta += "\t\t\t<tipoCifrado>"+i.tipoCifrado+
          "</tipoCifrado>\n";
      consulta += "\t\t\t<accion>"+i.getAccion()+"</accion>\n";
      consulta += "\t\t</actividades>\n";
    }
    consulta += "\t</actividades>\n";
    consulta += "</ListaActividadesXML></xmp>";
    return consulta;
  }

  private List<Actividad> consultarRegistros() {
    try {
      File archivoXML = new File(rutaArchivo);
      ObjectMapper mapper = new XmlMapper();
      InputStream inputStream = new FileInputStream(archivoXML);
      TypeReference<ListaActividadesXML> typeReference = new TypeReference<ListaActividadesXML>() {};
      listaActividades = mapper.readValue(inputStream, typeReference);
      inputStream.close();
      return listaActividades.getActividades();
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
