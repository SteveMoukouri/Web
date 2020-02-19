package tools;


public class User_tools {
	static String table = "DB_users_cai_moukouri";
	static String attributs = "id,login,password,prenom,nom,pseudo,mail,sexe,date_de_naissance";
	
	public static boolean exist_user(String login) throws Exception {
		return Mini_tools.in_bd(login, "login", table);
	}
}
