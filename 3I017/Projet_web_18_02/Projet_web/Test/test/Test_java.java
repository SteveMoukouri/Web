package test;
import org.json.JSONException;
import org.json.JSONObject;
import services.*;

public class Test_java {
	public static void main(String[] args) throws JSONException {
		System.out.println("hello");
		JSONObject a = new JSONObject();
		a = User_services.createUser("4646", "123456", "cai", "eddy");
		System.out.println(a);
		JSONObject b = new JSONObject();
		b = Authentification_services.login("Eddy","zkjjzjz");
		System.out.println(b);
		return;
	}
}