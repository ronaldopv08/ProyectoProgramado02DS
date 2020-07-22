package logicadeservicios;

import java.util.List;
import org.json.JSONObject;
import com.ibm.watson.developer_cloud.assistant.v1.Assistant;
import com.ibm.watson.developer_cloud.assistant.v1.model.Context;
import com.ibm.watson.developer_cloud.assistant.v1.model.InputData;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageOptions;
import com.ibm.watson.developer_cloud.assistant.v1.model.MessageResponse;
import com.ibm.watson.developer_cloud.service.security.IamOptions;

/**
 * Clase simple de acceso a los servicios de computación cognitiva de IBM Watson Assistant v1
 * @author Gabriel Cortés Mena
 * @author Ronaldo Picado Vega
 */
@SuppressWarnings("deprecation")
public class ServicioWatson {
  private final String apiKey="OQk1PFhYjmF3yjMD9BwQ_C4uvSlvLxgW2SMrlHrcb5cf";
  private final String assistantURL="https://api.us-south.assistant.watson.cloud.ibm.com/instances/4e626345-c978-49e6-a460-cc741c600dcb";
  private final String workspaceId = "a550de2a-f7d1-428e-a7ba-8b8f06b96813";
  private Assistant asistente;
  private MessageResponse respuestaAsistente;
  
  /**
   * Método constructor de la clase, no recibe parámetros.
   */
  public ServicioWatson() {
    IamOptions autenticador = new IamOptions.Builder().apiKey(apiKey).build();
    asistente = new Assistant("2020-06-19", autenticador);
    asistente.setEndPoint(assistantURL);
  }
  
  /**
   * Método para enviar variables al contexto de la conversación
   * @param contextoConversacion Contexto actual de la conversación, en formato String
   * @param variableContexto Nombre de la variable de contexto, en formato String
   * @param valorVariable Valor de la variable, en formato String
   * @param mensajeConversacion Mensaje de la conversación, en formato String
   */
  public void enviarContexto(String contextoConversacion, String variableContexto, 
      String valorVariable, String mensajeConversacion) {
    JSONObject contextoJson = new JSONObject(contextoConversacion);
    Context contexto = new Context();
    contexto.put(variableContexto,valorVariable);
    contexto.putAll(contextoJson.toMap());
    InputData entrada = new InputData.Builder(mensajeConversacion).build();
    MessageOptions opciones = new MessageOptions.Builder(workspaceId).input(entrada)
        .context(contexto).build();
    respuestaAsistente = asistente.message(opciones).execute();
  }
  
  /**
   * Método para obtener el valor de una variable de contexto.
   * @param nombreVariable Nombre de la variable de contexto, en formato String.
   * @return Valor de la variable de contexto, en formato Object.
   */
  public Object obtenerVariableContexto(String nombreVariable) {
    return respuestaAsistente.getContext().get(nombreVariable);
  }
  
  /**
   * Método para obtener las respuestas del asistente
   * @return Objeto de tipo JSON con las respuestas obtenidas.
   */
  public JSONObject retornarConversacion(){
    List<String> assistantResponseList = respuestaAsistente.getOutput().getText();
    JSONObject object = new JSONObject();
    
    String assistantResponseText = "";
    for (String tmpMsg : assistantResponseList)
        assistantResponseText = assistantResponseText + System.lineSeparator() + tmpMsg;
    object.put("response", assistantResponseText);
    object.put("context", respuestaAsistente.getContext());
    return object;
  }
  
}
