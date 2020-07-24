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
import logicadeinstanciacion.CifradoFactory;
import logicadeinstanciacion.ServicioAnalizadorTonoSingleton;
import logicadeinstanciacion.ServicioBitacoraSingleton;
import logicadeinstanciacion.ServicioTraductorSingleton;
import logicadeinstanciacion.ServicioWatsonSingleton;
import logicadenegocios.Cifrable;
import logicadenegocios.Cifrado;
import logicadenegocios.Sustitucion;
import logicadeservicios.ServicioAnalizadorTono;
import logicadeservicios.ServicioBitacora;
import logicadeservicios.ServicioTraductor;
import logicadeservicios.ServicioWatson;

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
  private ServicioTraductor traductor;
  private ServicioAnalizadorTono analizadorTono;
  private ServicioBitacora bitacora;

  /**
   * Método constructor de la clase, sin parámetros
   */
  public ControladorPrincipal(){
    cifradoFactory = new CifradoFactory();
    asistente = ServicioWatsonSingleton.getInstance();
    traductor = ServicioTraductorSingleton.getInstance();
    analizadorTono = ServicioAnalizadorTonoSingleton.getInstance();
    bitacora = ServicioBitacoraSingleton.getInstance();
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
    datos.add(texto);
    datos.add(metodo);
    datos.add(opcion);
    
    if(validarDatos(datos)) {
      datos.add(clave);
      String respuesta = obtenerRespuesta(datos);
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

  private String obtenerRespuesta(List<String> pDatos) {
    String texto = extraerTexto(pDatos.get(0));
    cifrado = solicitarCifrado(pDatos.get(1));
    if(!pDatos.get(3).equals("null")) {
      String clave = extraerClave(pDatos.get(3));
      Method method;
      try {
        method = Sustitucion.class.getDeclaredMethod("setClave", String.class);
        method.setAccessible(true);
        method.invoke(cifrado, clave);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    pDatos.set(0, texto);
    return ejecutarCifrado(pDatos);
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

  private String ejecutarCifrado(List<String> pDatos) {
    if (pDatos.get(2).equals("codificacion")) {
      if(validarTexto(pDatos.get(0))) {
        return "Rechazado";
      }
      registrarActividad(pDatos);
      return cifrado.cifrar(pDatos.get(0).replaceAll("\"", ""));
    }
    registrarActividad(pDatos);
    return cifrado.descifrar(pDatos.get(0).replaceAll("\"", ""));
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
  
  private boolean validarTexto(String pTexto) {
    String texto = traductor.ejecutarTraduccion(pTexto);
    return analizadorTono.verificarEnfado(texto);
  }
  
  private void registrarActividad(List<String> pDatos) {
    bitacora.registarActividad(pDatos);
  }
  
}
