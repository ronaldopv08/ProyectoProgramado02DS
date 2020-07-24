package logicadenegocios;

import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase simple para cifrar por Codigo Telefonico
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 * @version 1.0
 */
public class CodigoTelefonico extends Cifrado {
  
  private Hashtable<String,String> caracteres; 
  
  /**
   * Método constructor de la clase, sin parámetros
   */ 
  public CodigoTelefonico() {
    caracteres = new Hashtable<String,String>();
    completarCaracteres();
  }

  @Override
  public String cifrar(String pTexto) {
    pTexto = pTexto.toLowerCase();
    if(validarMensaje(pTexto)){
      return "N/A";
    }
    String nuevoTexto = "";
    for(String i : pTexto.split("")) {
      nuevoTexto += caracteres.get(i) + " ";         
    }
    return nuevoTexto;
  }

  @Override
  public String descifrar(String pTexto) {
    if(validarMensaje(pTexto)){
      return "N/A";
    }
    Map<String, String> mapInversed = caracteres.entrySet().stream().
        collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    String nuevoTexto = "";
    for(String i : pTexto.split(" ")) {
      nuevoTexto += mapInversed.get(i);         
    }
    return nuevoTexto;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    return !validarMensajeCodigoTelefonico(pTexto) & !validarMensajeTexto(pTexto);
  }
  
  private boolean validarMensajeCodigoTelefonico(String pTexto) {
    if(pTexto.isEmpty()) {
      return false;
    }
    return pTexto.matches("[0-9\\*\\ ]*");
  }
  
  private boolean validarMensajeTexto(String pTexto) {
    if(pTexto.isEmpty()) {
      return false;
    }
    return pTexto.matches("[a-z\\ ]*"); 
  }
  
  private void completarCaracteres() {
    caracteres.put("a", "21");
    caracteres.put("b", "22");
    caracteres.put("c", "23");
    caracteres.put("d", "31");
    caracteres.put("e", "32");
    caracteres.put("f", "33");
    caracteres.put("g", "41");
    caracteres.put("h", "42");
    caracteres.put("i", "43");
    caracteres.put("j", "51");
    caracteres.put("k", "52");
    caracteres.put("l", "53");
    caracteres.put("m", "61");
    caracteres.put("n", "62");
    caracteres.put("o", "63");
    caracteres.put("p", "71");
    caracteres.put("q", "72");
    caracteres.put("r", "73");
    caracteres.put("s", "74");
    caracteres.put("t", "81");
    caracteres.put("u", "82");
    caracteres.put("v", "83");
    caracteres.put("w", "91");
    caracteres.put("x", "92");
    caracteres.put("y", "93");
    caracteres.put("z", "94");
    caracteres.put(" ", "*");
  }

}
