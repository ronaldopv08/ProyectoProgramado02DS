package logicadeintegracion;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("/iniciosesionadministrador")
public class ControladorInicioSesionAdministrador {

  private final String usuario = "admin";
  private final String contrasena = "admin";

  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {
    return "<!DOCTYPE html>\r\n" + "<html>\r\n" + "  <head>\r\n"
        + "    <meta charset=\"UTF-8\">\r\n" + "    <title>Inicio Sesion Administrador</title>\r\n"
        + "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/"
        + "css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiF"
        + "eWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\r\n"
        + "  </head>\r\n" + "  \r\n" + "  <body>\r\n" + "    <div class=\"row\">\r\n"
        + "      <div class=\"container col-lg-3\">\r\n" + "        <div class=\"form-group\">\r\n"
        + "          <h1>Inicio Sesion</h1>\r\n" + "        </div>\r\n"
        + "        <form:form method=\"post\" commandName=\"usuario\">\r\n" + "          \r\n"
        + "          <form:errors path=\"*\" element=\"div\" cssClass=\"alert alert-danger\"/>\r\n"
        + "            \r\n"
        + "          <form method=\"post\" action=\"../chatbot/iniciosesionadministrador\" >  \r\n"
        + "            <div class=\"form-group\">\r\n"
        + "              <form:label path=\"usuario\">Nombre de usuario:</form:label>\r\n"
        + "         <input type=\"text\" id=\"usuario\" name=\"usuario\" class=\"form-control\" "
        + "required />\r\n"
        + "            </div>\r\n" + "         \r\n" + "            <div class=\"form-group\">\r\n"
        + "              <form:label path=\"contrasena\">Contraseña:</form:label>\r\n"
        + "              <input id=\"contrasena\" name=\"contrasena\" type=\"password\" class=\"fo"
        + "rm-control\"  required/>\r\n"
        + "            </div>\r\n" + "              \r\n"
        + "              <div class=\"form-group\">\r\n"
        + "                <input type=\"submit\" value=\"Iniciar Sesion\" class=\"btn btn-primary"
        + " btn-block\"/>\r\n"
        + "              </div>\r\n" + "            </form>\r\n" + "        </form:form>\r\n"
        + "      </div>\r\n" + "\r\n" + "    </div>\r\n" + "        \r\n" + "\r\n" + "  </body>\r\n"
        + "</html>";
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void createMessage(@FormParam("usuario") String usuario,
      @FormParam("contrasena") String contrasena, @Context HttpServletResponse servletResponse) {
    String ventana = "../chatbot/iniciosesionadministrador";
    try {
      if (validarCredencialesAdministrador(usuario, contrasena)) {
        ventana = "../chatbot/moduloadministracion";
      }
      servletResponse.sendRedirect(ventana);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private boolean validarCredencialesAdministrador(String pUsuario, String pContrasena) {
    return pUsuario.equals(this.usuario) && pContrasena.equals(this.contrasena);
  }

}
