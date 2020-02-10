package tools;
import org.json.simple.JSONObject;

public class ErrorJSON {
	public static JSONObject serviceRefused(String message, int codeErreur) {
		JSONObject obj = new JSONObject();
		obj.put(message,codeErreur);    
		return obj;
	}
	public static JSONObject serviceAccepted() {
		JSONObject obj = new JSONObject(); 
		return obj;
	}
	
}
