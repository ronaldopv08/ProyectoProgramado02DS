package logicadenegocios;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class ListaActividadesXML {
  
  //@JacksonXmlElementWrapper(localName="actividades")
  private List<Actividad> actividades;

  public ListaActividadesXML() {
    actividades = new ArrayList<Actividad>();
  }
  
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
