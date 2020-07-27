package logicadenegocios;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

/**
 * Clase simple para asignar etiquetas de envoltorio a la colección
 * de objetos tipo Actividad en archivos .xml
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ListaActividadesXML {
  
  private List<Actividad> actividades;

  /**
   * Método constructor de la clase
   */
  public ListaActividadesXML() {
    actividades = new ArrayList<Actividad>();
  }
  
  /**
   * Método que permite agregar objetos de tipo Actividad al atributo actividades
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
