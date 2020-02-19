package test;

import java.util.ArrayList;

public class Test_JDBC {
	public static void main(String [] args) throws Exception{
		ArrayList<String>res = tools.Mini_tools.requete("Select prenom From DB_users_cai_moukouri ;");
		for(String i :res) {
			if(i.compareTo("Daoud")==0) {
				System.out.println("hello");
			}
		}
		
		
	}
}
