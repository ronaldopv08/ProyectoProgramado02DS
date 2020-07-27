package logicadeservicios;

import java.util.ArrayList;
import java.util.List;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.tone_analyzer.v3.model.ToneAnalysis;
import com.ibm.watson.tone_analyzer.v3.model.ToneOptions;
import com.ibm.watson.tone_analyzer.v3.model.ToneScore;

/**
 * Clase para acceder a los servicios de análisis de sentimiento de  IBM
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioAnalizadorTono {
  
  private String apiKey = "ZnTrY_s40qWLnF046hypKaRfHHfVOAArT5JM3QHyxCGx";
  private ToneAnalyzer analizador;

  /**
   * Método constructor de la clase
   */
  public ServicioAnalizadorTono() {
    Authenticator autenticador = new IamAuthenticator(apiKey);
    analizador = new ToneAnalyzer("2017-09-21", autenticador);
  }
  
  /**
   * Método para verificar la existencia del sentimiento enfado en un texto
   * @param texto Texto por verificar, formato String
   * @return confirmación del sentimiento enfado, en formato boolean
   */
  public boolean verificarEnfado(String texto) {
    List<String> sentimientos = ejecutarAnalisis(texto);
    for (String sentimiento : sentimientos) {
      if (sentimiento.equalsIgnoreCase("Anger")) {
        return true;
      }
    }
    return false;
  }
  
  private  ArrayList<String> ejecutarAnalisis(String texto) {
    ToneOptions opcionesTono = new ToneOptions.Builder().text(texto).build();
    ToneAnalysis respuesta = analizador.tone(opcionesTono).execute().getResult();
    List<ToneScore> tonos = respuesta.getDocumentTone().getTones();
    ArrayList<String> sentimientos = new ArrayList<>();
    for (ToneScore tono : tonos) {
      sentimientos.add(tono.getToneName());
    }
    return sentimientos;
  }
  
}
