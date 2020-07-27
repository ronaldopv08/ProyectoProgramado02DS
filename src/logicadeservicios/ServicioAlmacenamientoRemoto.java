package logicadeservicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;

/**
 * Clase simple para acceder a los servicios de almacenamiento en la nube por medio de
 * la api DropBox apiv2
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioAlmacenamientoRemoto {

  private String tokenAcceso = "n87gJvhBeTAAAAAAAAAADwryGz8xJ0rCGDLAmGKl-_2TzFov2CeEa18cWR41sug5";
  private DbxClientV2 cliente;
  
  /**
   * Método constructor de la clase
   */
  public ServicioAlmacenamientoRemoto() {
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/Proyecto02DSBitacoras").build();
    cliente = new DbxClientV2(config, tokenAcceso);
  }
  
  /**
   * Método para guardar un archivo en el almacenamiento en la nube de DropBox
   * @param archivo Objeto de tipo File que contiene la ruta de acceso al archivo
   */
  public void subirArchivo(File archivo) {
    try {
      InputStream archivoIs = new FileInputStream(archivo);
      FileMetadata metadata = cliente.files().
          uploadBuilder("/"+archivo.getName()).withMode(WriteMode.OVERWRITE).
          uploadAndFinish(archivoIs);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  /**
   * Método para descargar un archivo almacenado en la nube
   * @param nombreArchivo nombre del archivo con formato incluido, formato String
   * @return Objeto de tipo File con la ruta de acceso al archivo descargado
   */
  public File descargarArchivo(String nombreArchivo) {
    try {
      OutputStream outputStream = new FileOutputStream(nombreArchivo);
      FileMetadata metadata = cliente.files()
              .downloadBuilder("/"+nombreArchivo)
              .download(outputStream);
      outputStream.close();
      File archivo = new File(nombreArchivo);
      return archivo;
    }catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

}