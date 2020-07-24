package logicadenegocios;
import java.util.Hashtable;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Clase simple para cifrar por medio del algoritmo de codificación binaria
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class CodigoBinario extends Cifrado {
  
  private Hashtable<String, String> caracteres;

  /**
   * Método constructor de la clase, no recibe parámetros.
   */
  public CodigoBinario() {
    caracteres = completarCaracteres();
  }
  
  @Override
  public String cifrar(String pTexto) {
    pTexto = pTexto.toLowerCase();
    if(!validarMensaje(pTexto)) {
      return "N/A";
    }
    String textoCifrado = "";
    for (String letra : pTexto.split("")) {
      textoCifrado += caracteres.get(letra)+" ";
    }
    return textoCifrado;
  }

  @Override
  public String descifrar(String pTexto) {
    Map<String, String> caracteresInverso = caracteres.entrySet().stream().
        collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    if(!validarMensajeCifrado(pTexto)) {
      return "N/A";
    }
    String textoDescifrado = "";
    for (String letra : pTexto.split(" ")) {
      textoDescifrado += caracteresInverso.get(letra);
    }
    return textoDescifrado;
  }

  @Override
  protected boolean validarMensaje(String pTexto) {
    if (pTexto.isEmpty()) {
      return false;
    }
    return pTexto.matches("[a-z\\ ]*"); 
  }
  
  
  private boolean validarMensajeCifrado(String pTexto) {
    if (pTexto.isEmpty()) {
      return false;
    }
    return pTexto.matches("[0-9\\*\\ ]*");
  }
  
  private Hashtable<String, String> completarCaracteres(){
    Hashtable<String, String> hashTable = new Hashtable<>(26);
    hashTable.put("a", "00000");
    hashTable.put("b", "00001");
    hashTable.put("c", "00010");
    hashTable.put("d", "00011");
    hashTable.put("e", "00100");
    hashTable.put("f", "00101");
    hashTable.put("g", "00110");
    hashTable.put("h", "00111");
    hashTable.put("i", "01000");
    hashTable.put("j", "01001");
    hashTable.put("k", "01010");
    hashTable.put("l", "01011");
    hashTable.put("m", "01100");
    hashTable.put("n", "01101");
    hashTable.put("o", "01110");
    hashTable.put("p", "01111");
    hashTable.put("q", "10000");
    hashTable.put("r", "10001");
    hashTable.put("s", "10010");
    hashTable.put("t", "10011");
    hashTable.put("u", "10100");
    hashTable.put("v", "10101");
    hashTable.put("w", "10110");
    hashTable.put("x", "10111");
    hashTable.put("y", "11000");
    hashTable.put("z", "11001");
    hashTable.put(" ", "*");
    return hashTable;
  }

}
