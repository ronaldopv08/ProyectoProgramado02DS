package logicadeintegracion;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/moduloadministracion") 
public class ControladorModuloAdministracion {
  
  @GET  
  @Produces(MediaType.TEXT_HTML)  
  public String sayHtmlHello() {  
    return "<!DOCTYPE html>\r\n" + 
        "<html>\r\n" + 
        "  <head>\r\n" + 
        "    <meta charset=\"UTF-8\">\r\n" + 
        "    <title>Inicio Sesion Administrador</title>\r\n" + 
        "    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">\r\n" + 
        "  </head>\r\n" + 
        "  \r\n" + 
        "  <body>\r\n" + 
        "    <div class=\"row\">\r\n" + 
        "      <div class=\"container col-lg-3\">\r\n" + 
        "        <div class=\"form-group\">\r\n" + 
        "          <h1>hola</h1>\r\n" + 
        "        </div>\r\n" + 
        "        <form:form method=\"post\" commandName=\"usuario\">\r\n" + 
        "          \r\n" + 
        "          <form:errors path=\"*\" element=\"div\" cssClass=\"alert alert-danger\"/>\r\n" + 
        "            \r\n" +  
        "            <form method=\"post\" action=\"http://localhost:9080/ProyectoProgramado02DS/chatbot/iniciosesionadministrador\" >  \r\n" + 
        "            <div class=\"form-group\">\r\n" + 
        "              <form:label path=\"usuario\">Nombre de usuario:</form:label>\r\n" + 
        "              <input type=\"text\" id=\"usuario\" name=\"usuario\" class=\"form-control\" required />\r\n" + 
        "            </div>\r\n" + 
        "         \r\n" + 
        "            <div class=\"form-group\">\r\n" + 
        "              <form:label path=\"contrasena\">Contraseña:</form:label>\r\n" + 
        "              <input id=\"contrasena\" name=\"contrasena\" type=\"password\" class=\"form-control\"  required/>\r\n" + 
        "            </div>\r\n" + 
        "              \r\n" +
        "              <div class=\"form-group\">\r\n" + 
        "                <input type=\"submit\" value=\"Iniciar Sesion\" class=\"btn btn-primary btn-block\"/>\r\n" + 
        "              </div>\r\n" + 
        "            </form>\r\n" + 
        "        </form:form>\r\n" + 
        "      </div>\r\n" + 
        "\r\n" + 
        "    </div>\r\n" + 
        "        \r\n" + 
        "\r\n" + 
        "  </body>\r\n" + 
        "</html>";  
  }

}
