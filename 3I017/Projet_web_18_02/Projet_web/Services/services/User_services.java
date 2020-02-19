package services;

import org.json.JSONException;
import org.json.JSONObject;
import tools.*;

public class User_services {
	
	public static JSONObject getUser() {
		return null;
	}
	
	public static JSONObject getUserlist() {
		return null;
	}
	
	
	public static JSONObject createUser (String login, String password, String nom, String prenom) throws JSONException {
		if(Mini_tools.null_vide(login) || Mini_tools.null_vide(password) || Mini_tools.null_vide(nom) || Mini_tools.null_vide(prenom)) {
			return ErrorJSON.serviceRefused("argument vide/null pour la méthode UserCreate", -1);
		}
		
		// VERIF SI USER EXIST
		
		//CREER USER DANS BD

		
		return ErrorJSON.serviceAccepted();
	}
	
	public static JSONObject deleteUser() {
		return null;
	}
	

}


