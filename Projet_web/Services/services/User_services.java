package services;

import org.json.simple.JSONObject;
import tools.*;

public class User_services {
	
	public static JSONObject login (String login, String password) {
		if(User_tools.null_vide(login) || tools.User_tools.null_vide(password)) {
			return ErrorJSON.serviceRefused("argument vide/null pour la méthode UserCreate", -1);
		}
		// VERIF BD login/password
		
		JSONObject obj = ErrorJSON.serviceAccepted();
		obj.put("key", tools.User_tools.generation_token());
		
		// INSERER DANS LA BD DE CONNEXION
		
		return obj;
	}
	
	
	public static JSONObject CreateUser (String login, String password, String nom, String prenom) {
		if(User_tools.null_vide(login) || tools.User_tools.null_vide(password) || tools.User_tools.null_vide(nom) || tools.User_tools.null_vide(prenom)) {
			return ErrorJSON.serviceRefused("argument vide/null pour la méthode UserCreate", -1);
		}
		
		// VERIF SI USER EXIST
		
		//CREER USER DANS BD

		
		return ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject logout() {
		
		// A FAIRE ...
		return ErrorJSON.serviceAccepted();
	}

}


