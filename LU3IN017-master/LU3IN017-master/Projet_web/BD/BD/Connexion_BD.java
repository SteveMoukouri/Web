package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion_BD {
	public static Connection com;
	public static Statement st;
	

	public static void open_connexion_BD() throws SQLException {
		String URL = Static_BD.mysql_host;
		Connexion_BD.com = DriverManager.getConnection(URL,Static_BD.mysql_user,Static_BD.mysql_password);
	}
	
	public static void close_connexion_BD() throws SQLException {
		Connexion_BD.com.close();
	}
	
	

}
