package logicadeintegracion;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

@Path("/iniciosesionadministrador")
public class ControladorInicioSesionAdministrador {

  private final String usuario = "admin";
  private final String contrasena = "admin";
  @Context
  private ServletContext servletContext;

  /**
   * Método para retornar un html en formato String para reproducir en el sitio web
   * @return html en formato String
   */
  @GET
  @Produces(MediaType.TEXT_HTML)
  public String sayHtmlHello() {   
    String base = servletContext.getRealPath("/WEB-INF/inicioSesionAdministrador.html");
    try {
      File file = new File(base);
      String content = Files.toString(file,Charsets.UTF_8);
      return content;
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Método para enviar la información de inicio de sesión para un usuario administrador
   * @param usuario String con la información del nombre de usuario
   * @param contrasena String con la información de la contraseña
   * @param servletResponse Objeto de tipo ServletResponse
   */
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
