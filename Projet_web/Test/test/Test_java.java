package test;
import org.json.simple.JSONObject;
import services.User_services;

public class Test_java {
	public static void main(String[] args) {
		System.out.println("hello");
		JSONObject a = new JSONObject();
		a = User_services.CreateUser("4646", "123456", "cai", "eddy");
		System.out.println(a);
		JSONObject b = new JSONObject();
		b = User_services.login("Eddy","zkjjzjz");
		System.out.println(b);
		return;
	}
}