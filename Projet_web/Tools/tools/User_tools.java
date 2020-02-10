package tools;
import java.sql.*;
import java.security.SecureRandom;
import org.json.simple.JSONObject;

public class User_tools {
	
	public static boolean null_vide(String args) {
		return (args == null || args == "");
	}

	/*
	public static boolean exist_user(String login) {
		if(login in ..) {
			return true
		}
		return false
	}
	*/
	
	public static String generation_token() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}
	
	

}
