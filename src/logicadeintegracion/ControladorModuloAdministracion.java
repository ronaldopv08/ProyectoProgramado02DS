package logicadeintegracion;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.ServletContext;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import logicadeinstanciacion.ServicioBitacoraSingleton;
import logicadeservicios.ServicioBitacora;

@Path("/moduloadministracion") 
public class ControladorModuloAdministracion {
  
  private ServicioBitacora servicioBitacora = ServicioBitacoraSingleton.getInstance();
  @Context
  private ServletContext servletContext;
  
  /**
   * Método para retornar un html en formato String para reproducir en el sitio web
   * @return html en formato String
   */
  @GET  
  @Produces(MediaType.TEXT_HTML)  
  public String sayHtmlHello() {
    String base = servletContext.getRealPath("/WEB-INF/moduloAdministracion.html");
    try {
      File file = new File(base);
      @SuppressWarnings("deprecation")
      String content = Files.toString(file,Charsets.UTF_8);
      return content;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    } 
  }
  
  /**
   * Método que envía la información de un archivo de bitácora seleccionado en formato String
   * para ser presentado en el sitio web
   * @param tipobitacora Tipo de bitacora seleccionado, formato String
   * @param filtroconsulta Tipo de consulta seleccionada, formato String
   * @return
   */
  @POST  
  @Path("/consultarregistros")  
  public Response consultarRegistros(@FormParam("tipobitacora") String tipobitacora,
      @FormParam("filtroconsulta") String filtroconsulta) {
    String consulta;
    try {
      consulta = servicioBitacora.consultarRegistros(tipobitacora, filtroconsulta);
      return Response.status(200).entity(consulta).build(); 
    } catch (NoSuchMethodException | SecurityException | IllegalAccessException
        | IllegalArgumentException | InvocationTargetException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return Response.status(200).entity("N/A").build(); 
    } 
  }
  
}
