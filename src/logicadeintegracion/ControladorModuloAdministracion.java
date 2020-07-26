package logicadeintegracion;

import java.lang.reflect.InvocationTargetException;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import logicadeinstanciacion.ServicioBitacoraSingleton;
import logicadeservicios.ServicioBitacora;

@Path("/moduloadministracion") 
public class ControladorModuloAdministracion {
  
  private ServicioBitacora servicioBitacora = ServicioBitacoraSingleton.getInstance();
  
  @GET  
  @Produces(MediaType.TEXT_HTML)  
  public String sayHtmlHello() {  
    return "<!DOCTYPE html>\r\n" + 
        "<html>\r\n" + 
        "  <head>\r\n" + 
        "    <meta charset=\"UTF-8\" />\r\n" + 
        "    <title>Modulo Administracion</title>\r\n" + 
        "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\r\n" + 
        "  </head>\r\n" + 
        "    <body>\r\n" + 
        "    <div class=\"row\" >\r\n" + 
        "    <div class=\"col-md-4 mx-auto\">\r\n" + 
        "        <div class=\"card\">\r\n" + 
        "            <div class=\"card-header\">\r\n" + 
        "               <h1>Modulo Administracion</h1> \r\n" + 
        "            </div>\r\n" + 
        "            <div class=\"card-body\">\r\n" + 
        "              <form:form method=\"post\">\r\n" + 
        "        <form method=\"post\" action=\"../chatbot/moduloadministracion/consultarregistros\" >\r\n" + 
        "                    <div class=\"form-group\">\r\n" + 
        "            <label path=\"tipoBitacora\">Tipo Bitacora</label>\r\n" + 
        "                        <select name=\"tipobitacora\" id=\"tipobitacora\">\r\n" + 
        "                <option value=\"BitacoraCSV\">CSV</option>\r\n" + 
        "                <option value=\"BitacoraXML\">XML</option>\r\n" + 
        "                <option value=\"BitacoraTramaPlana\">Trama Plana</option>\r\n" + 
        "            </select>\r\n" + 
        "                    </div>\r\n" + 
        "                    <div class=\"form-group\">\r\n" + 
        "            <label path=\"filtroConsulta\">Filtro de Consulta</label>\r\n" + 
        "                        <select name=\"filtroconsulta\" id=\"filtroconsulta\">\r\n" + 
        "                <option value=\"consultarTodosRegistros\">Todos los Registros</option>\r\n" + 
        "                <option value=\"consultarCodificaciones\">Registros de Codificacion</option>\r\n" + 
        "                <option value=\"consultarDecodificaciones\">Registros de Decodificacion</option>\r\n" + 
        "                <option value=\"consultarAccionesHoy\">Registros de Hoy</option>\r\n" + 
        "            </select>\r\n" + 
        "                    </div>\r\n" + 
        "                    <div class=\"form-group\">\r\n" + 
        "                        <button type=\"submit\" class=\"btn btn-primary btn-block\">\r\n" + 
        "                            Consultar Bitacora\r\n" + 
        "                        </button>\r\n" + 
        "                    </div>\r\n" + 
        "          </form>\r\n" + 
        "                </form:form>\r\n" + 
        "              \r\n" + 
        "            </div>\r\n" + 
        "        </div>\r\n" + 
        "    </div>\r\n" + 
        "    </div>\r\n" + 
        "\r\n" + 
        "  </body>\r\n" + 
        "</html>";  
  }
  
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
