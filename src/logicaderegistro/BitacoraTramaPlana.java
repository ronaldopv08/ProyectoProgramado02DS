package logicaderegistro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import logicadeinstanciacion.ServicioAlmacenamientoRemotoSingleton;
import logicadenegocios.Actividad;

public class BitacoraTramaPlana extends Bitacora {
  
  public BitacoraTramaPlana() {
    servicio = ServicioAlmacenamientoRemotoSingleton.getInstance();
    this.rutaArchivo = servicio.descargarArchivo("bitacora.txt").getAbsolutePath();
  }
  
  @Override
  public void registrarActividad(Actividad pActividad) {
    try {
      File file = new File(rutaArchivo);
      Writer output = new BufferedWriter(new FileWriter(file, true));
      String nuevaActividad = "\n" + pActividad.getFecha() + "\t" + pActividad.hora + "\t" + 
          pActividad.getTipoCifrado() + "\t" + pActividad.getAccion();
      output.write(nuevaActividad);
      output.close();
      servicio.subirArchivo(file);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }

  @Override
  protected String consularTodosRegistros() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String consultarCodifcaciones() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String consultarDecodificaciones() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected String consultarAccionesHoy() {
    // TODO Auto-generated method stub
    return null;
  }

}
