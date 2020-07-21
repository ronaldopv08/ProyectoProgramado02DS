package logicadeintegracion;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import logicadenegocios.Cifrable;
import logicadenegocios.Cifrado;
import logicadenegocios.CifradoFactory;
import logicadenegocios.Sustitucion;
import logicadeservicios.ServicioWatson;
import logicadeservicios.ServicioWatsonSingleton;

/**
 * Clase diseñada para controlar el acceso a las funcionalidades de la aplicacion
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
@Path("/chatservice")
public class ControladorPrincipal {

  private CifradoFactory cifradoFactory;
  private Cifrable cifrado;
  private ServicioWatson asistente;

  /**
   * Método constructor de la clase, sin parámetros
   */
  public ControladorPrincipal(){
    cifradoFactory = new CifradoFactory();
    asistente = ServicioWatsonSingleton.getInstance();
  }

  /**
   * Metodo que controla los datos de la aplicacion
   * 
   * @param conversationMsg String que representa la conversacion
   * @param conversationCtx String que representa el contexto de la conversacion
   * @return Response de los nuevos datos
   */
  @GET
  @Produces("application/json")
  public Response getResponse(@QueryParam("conversationMsg") String conversationMsg, 
      @QueryParam("conversationCtx") String conversationCtx) {

    asistente.enviarContexto(conversationCtx, "saludo", obtenerSaludo(), conversationMsg);
    String metodo = (String) asistente.obtenerVariableContexto("metodo");
    String opcion = (String) asistente.obtenerVariableContexto("opcion");
    String texto = (String) asistente.obtenerVariableContexto("texto");
    String clave = String.valueOf(asistente.obtenerVariableContexto("clave"));
    
    List<String> datos = new ArrayList<>();
    datos.add(metodo);
    datos.add(opcion);
    datos.add(texto);
    
    if(validarDatos(datos)) {
      String respuesta = obtenerRespuesta(clave, texto, metodo, opcion);
      asistente.enviarContexto(conversationCtx, "respuesta", respuesta, conversationMsg);
    }
    return Response.status(Status.OK).entity(asistente.retornarConversacion().toString()).build();
  }
  
  private boolean validarDatos(List<String> pDato) {
    for (String i: pDato) {
      if(i==null) {
        return false;
      }
    }
    return true;
  }

  private String obtenerRespuesta(String clave, String texto, String metodo, String opcion) {
    texto = extraerTexto(texto);
    cifrado = solicitarCifrado(metodo);
    if(!clave.equals("null")) {
      clave = extraerClave(clave);
      Method method;
      try {
        method = Sustitucion.class.getDeclaredMethod("setClave", String.class);
        method.setAccessible(true);
        method.invoke(cifrado, clave);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return ejecutarCifrado(opcion, texto);
  }

  private String obtenerSaludo() {
    Integer hora = java.time.LocalTime.now().getHour()-6;
    if(Math.abs(hora-6)+Math.abs(11-hora)==Math.abs(11-6)) {
      return "Buenos días";
    }else if(Math.abs(hora-12)+Math.abs(17-hora)==Math.abs(17-12)) {
      return "Buenas tardes";
    }
    return "Buenas noches";
  }

  private Cifrado solicitarCifrado(String tipo) {
    try {
      return cifradoFactory.crearCifrado(tipo);
    } catch (Exception e) {
      return null;
    }
  }

  private String ejecutarCifrado(String pOpcion, String pTexto) {
    if (pOpcion.equals("codificacion")) {
      return cifrado.cifrar(pTexto.replaceAll("\"", ""));
    }else {
      return cifrado.descifrar(pTexto.replaceAll("\"", ""));
    }
  }

  private String extraerClave(String pTexto) {
    Matcher matcher = Pattern.compile("(?<=llave \").[^\"]*(?=\")").matcher(pTexto);
    if (matcher.find()){
      return matcher.group();
    }
    return pTexto;
  }

  private String extraerTexto(String pTexto) {
    Matcher matcher = Pattern.compile("(?<=texto \").[^\"]*(?=\")").matcher(pTexto);
    if (matcher.find()){
      return matcher.group();
    }
    return pTexto;
  }
}
