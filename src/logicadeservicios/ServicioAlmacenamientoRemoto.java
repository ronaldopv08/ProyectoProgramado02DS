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

public class ServicioAlmacenamientoRemoto {

  private String tokenAcceso = "n87gJvhBeTAAAAAAAAAADwryGz8xJ0rCGDLAmGKl-_2TzFov2CeEa18cWR41sug5";
  private DbxClientV2 cliente;
  
  public ServicioAlmacenamientoRemoto() {
    DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/Proyecto02DSBitacoras").build();
    cliente = new DbxClientV2(config, tokenAcceso);
  }
  
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