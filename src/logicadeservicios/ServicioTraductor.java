package logicadeservicios;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

/**
 * Clase para acceder a los servicios de traducción de IBM
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 *
 */
public class ServicioTraductor {
  
  private String apiKey = "FokKQWJJLRxE6y90zX_OCSeUoPvdSO5iyf722hrm5efD";
  private LanguageTranslator traductor;

  /**
   * Método constructor de la clase
   */
  public ServicioTraductor() {
    Authenticator authenticator = new IamAuthenticator(apiKey);
    traductor = new LanguageTranslator("2018-05-01", authenticator);
  }
  
  /**
   * Método para traducir un texto ingresado
   * @param texto Texto por traducir, formato String
   * @return String con el texto traducido
   */
  public String ejecutarTraduccion(String texto) {
    TranslateOptions opcionesTraduccion = new TranslateOptions.Builder().addText(texto)
        .source(Language.SPANISH).target(Language.ENGLISH).build();
    TranslationResult resultadoTraduccion = traductor.translate(opcionesTraduccion)
        .execute().getResult();
    return resultadoTraduccion.getTranslations().get(0).getTranslation();
  }

}
