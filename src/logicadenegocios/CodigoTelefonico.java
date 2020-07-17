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
    completarCaracteres('a', 21);
  }

  @Override
  public String cifrar(String pTexto) {
    if(validarMensaje(pTexto) || pTexto.isEmpty()){
      return "N/A";
    }
    String nuevoTexto = "";
    Map<String, String> mapInversed = caracteres.entrySet().stream().
        collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    for(String i : pTexto.split("")) {
      nuevoTexto += mapInversed.get(i.toLowerCase()) + " ";         
    }
    return nuevoTexto;
  }

  @Override
  public String descifrar(String pTexto) {
    if(validarMensaje(pTexto) || pTexto.isEmpty()){
      return "N/A";
    }
    String nuevoTexto = "";
    for(String i : pTexto.split(" ")) {
      nuevoTexto += caracteres.get(i);         
    }
    return nuevoTexto;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    return validarMensajeCodigoTelefonico(pTexto) && validarMensajeTexto(pTexto);
  }
  
  private boolean validarMensajeCodigoTelefonico(String pTexto) {
    try {
      for(String i : pTexto.replace("*", "").replace("  ", " ").split(" ")) {
        Integer.parseInt(i);
        if(caracteres.get(i).equals(null)){
          return true;
        }
      }
      return false;
    } catch (NumberFormatException nfe){
      return true;
    }
  }
  
  private boolean validarMensajeTexto(String pTexto) {
    for (int i = 0; i < pTexto.length(); i++) {
      if(Character.isDigit(pTexto.charAt(i))) {
        return true;
      }
    }
    return false;
  }
  
  private void completarCaracteres(char pLetra, int pPosicion) {
    caracteres.put(String.valueOf(pPosicion),String.valueOf(pLetra));
    pLetra=(char) (pLetra+1);
    if (pLetra > 'z') {
      caracteres.put("*"," ");
      return;
    } else if (pPosicion==73 || pPosicion ==93) {
      completarCaracteres(pLetra, pPosicion+1);
    } else if (pPosicion%10 >= 3 ) {
      completarCaracteres(pLetra, pPosicion-pPosicion%10+11);
    }else {
      completarCaracteres(pLetra, pPosicion+1);
    }
  }

}
