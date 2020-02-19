package services;

import org.json.JSONException;
import org.json.JSONObject;
import tools.ErrorJSON;
import tools.Mini_tools;

public class Authentification_services {
	
	public static JSONObject login (String login, String password) throws JSONException {
		if(Mini_tools.null_vide(login) || Mini_tools.null_vide(password)) {
			return ErrorJSON.serviceRefused("argument vide/null pour la méthode UserCreate", -1);
		}
		// VERIF BD login/password
		
		JSONObject obj = ErrorJSON.serviceAccepted();
		obj.put("key", tools.Authentification_tools.generation_token());
		
		// INSERER DANS LA BD DE CONNEXION
		
		return obj;
	}
	
	public static JSONObject logout() {
		
		// A FAIRE ...
		return ErrorJSON.serviceAccepted();
	}
}
