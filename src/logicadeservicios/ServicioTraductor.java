package logicadeservicios;

import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.language_translator.v3.LanguageTranslator;
import com.ibm.watson.language_translator.v3.model.TranslateOptions;
import com.ibm.watson.language_translator.v3.model.TranslationResult;
import com.ibm.watson.language_translator.v3.util.Language;

public class ServicioTraductor {
  
  private String apiKey = "FokKQWJJLRxE6y90zX_OCSeUoPvdSO5iyf722hrm5efD";
  private LanguageTranslator servicioTraductor;

  public ServicioTraductor() {
    Authenticator authenticator = new IamAuthenticator(apiKey);
    servicioTraductor = new LanguageTranslator("2018-05-01", authenticator);
  }
  
  public String ejecutarTraduccion(String texto) {
    TranslateOptions opcionesTraduccion = new TranslateOptions.Builder().addText(texto)
        .source(Language.SPANISH).target(Language.ENGLISH).build();
    TranslationResult resultadoTraduccion = servicioTraductor.translate(opcionesTraduccion)
        .execute().getResult();
    return resultadoTraduccion.getTranslations().get(0).getTranslation();
  }

}
