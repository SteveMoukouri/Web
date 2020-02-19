package tools;


import java.util.ArrayList;
import org.json.JSONObject;

public class Friends_tools {
	
	public static boolean exists_friend(String args,String arg2, String type_args,String type_arg2, String table) throws Exception {
		ArrayList<String>res = Mini_tools.requeteGET("Select * From "+table+" Where "+type_args+"="+args+" and "+type_arg2+"="+arg2+";");
		if (res.size() != 0)  return true;
		return false;
	}
	public static boolean list_friend(String id) throws Exception {
		ArrayList<String>res = Mini_tools.requeteGET("Select id2,pseudo From `DB_users_cai_moukouri`,DB_friends_cai_moukouri Where DB_friends_cai_moukouri.id1=DB_users_cai_moukouri.id AND DB_friends_cai_moukouri.id1="+id+";");
		return false;
	}
	
	public static JSONObject message(String cle , Object value) throws Exception{
		JSONObject j = new JSONObject();
		j.put(cle, value);
		return j;
	}
	
}
