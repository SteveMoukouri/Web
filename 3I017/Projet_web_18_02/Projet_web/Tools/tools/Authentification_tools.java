package tools;

import java.security.SecureRandom;

public class Authentification_tools {
	public static String generation_token() {
		SecureRandom random = new SecureRandom();
		byte bytes[] = new byte[20];
		random.nextBytes(bytes);
		String token = bytes.toString();
		return token;
	}
	public static String attribut_authentification(){
		return "a1,a2,a3";
	}
}
