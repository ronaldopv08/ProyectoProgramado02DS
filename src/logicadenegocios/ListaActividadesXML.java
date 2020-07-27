package logicadenegocios;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

/**
 * Clase simple para asignar etiquetas de envoltorio a la colecci�n
 * de objetos tipo Actividad en archivos .xml
 * @author Gabriel Cort�s Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ListaActividadesXML {
  
  private List<Actividad> actividades;

  /**
   * M�todo constructor de la clase
   */
  public ListaActividadesXML() {
    actividades = new ArrayList<Actividad>();
  }
  
  /**
   * M�todo que permite agregar objetos de tipo Actividad al atributo actividades
   * @param pActividad Objeto de tipo Actividad
   */
  public void agregarActividad(Actividad pActividad) {
    actividades.add(pActividad);
  }

  public List<Actividad> getActividades() {
    return actividades;
  }

  public void setActividades(List<Actividad> actividades) {
    this.actividades = actividades;
  }
  
  

}
