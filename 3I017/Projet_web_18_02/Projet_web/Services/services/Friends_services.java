package services;

import org.json.JSONException;
import org.json.JSONObject;

import tools.ErrorJSON;
import tools.Friends_tools;
import tools.Mini_tools;

public class Friends_services {
	public static JSONObject addFriend(String id1, String id2) throws Exception {
		// INSERER LE MESSAGE DANS LA BD
			if(Friends_tools.exists_friend(id1, id2, "id1", "id2", "DB_friends_cai_moukouri")){
				return ErrorJSON.serviceRefused(id1+" est deja l'ami de : "+id2, 1);
			}
			if(Mini_tools.requeteSET("INSERT INTO `DB_friends_cai_moukouri` (`id1`, `id2`, `date`, `nb_ami_commun`) VALUES ("+id1+","+id2+", CURRENT_DATE(), '1');")){
				return Friends_tools.message(id1+"a comme nouveau ami :" , id2);
			}
			return ErrorJSON.serviceRefused("erreur d'insertion dans la table", 1);
		}
	
	public static JSONObject deleteFriend(String id1, String id2) throws JSONException, Exception {
		// DELETE LE MESSAGE DANS LA BD
		if(!(Friends_tools.exists_friend(id1, id2, "id1", "id2", "DB_friends_cai_moukouri"))){
			return ErrorJSON.serviceRefused(id1 +" et "+id2+"ne sont pas amis", 1);
		}
		if(Mini_tools.requeteSET("DELETE FROM `DB_friends_cai_moukouri` WHERE id1="+id1+" AND id2="+id2+ ";")){
			return Friends_tools.message(id1+"a supprimé l'ami d'id :" , id2);
		}
		
		return ErrorJSON.serviceRefused("erreur de suppression dans la table", 1);
	}
	
	public static JSONObject listFriend(String id) {
		// ACCEDER A LA BD POUR RECUPERER LES MESSAGES DE LA PERSONNE ID
		JSONObject obj = ErrorJSON.serviceAccepted();
		return obj;
	}
}
