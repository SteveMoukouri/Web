package services;

import org.json.simple.JSONObject;

import tools.ErrorJSON;

public class Friends_services {
	public static JSONObject addFriend(String text) {
		// INSERER LE MESSAGE DANS LA BD
		JSONObject obj = ErrorJSON.serviceAccepted();
		return obj;
	}
	
	public static JSONObject deleteFriend(String text) {
		// DELETE LE MESSAGE DANS LA BD
		JSONObject obj = ErrorJSON.serviceAccepted();
		return obj;
	}
	
	public static JSONObject listFriend(int id) {
		// ACCEDER A LA BD POUR RECUPERER LES MESSAGES DE LA PERSONNE ID
		JSONObject obj = ErrorJSON.serviceAccepted();
		return obj;
	}
}
