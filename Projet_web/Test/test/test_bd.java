package test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class test_bd {
	public static void main(String[] args) {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url="emacs@ppti-14-401-14.ufr-info-p6.jussieu.fr";
		Connection connexion = DriverManager.getConnection(url, "admin", "admin");
		Statement lecture = connexion.createStatement();
		ResultSet curseur = lecture.executeQuery("select...");
		while (curseur.next()) {
			System.out.println(curseur.getString("nom"));
			System.out.println(curseur.getInt("age"));
			}
		curseur.close();
		lecture.close();
		connexion.close();
		
	}
}
