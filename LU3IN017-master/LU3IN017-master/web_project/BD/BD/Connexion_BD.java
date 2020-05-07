package BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connexion_BD {
	public static Connection com;
	public static Statement st;
	public static MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
	public static MongoClient mongoClient = new MongoClient(connectionString);
	public static MongoDatabase database = mongoClient.getDatabase("DB_eddy");
	public static MongoCollection<Document> col = database.getCollection("test");
	
	/*public static void open_conn_mongoDB() {
		MongoClient mongoClient = new MongoClient(connectionString);
		MongoDatabase database = mongoClient.getDatabase("DB_eddy");
		MongoCollection<Document> col = database.getCollection("test");
	}*/

	public static void close_conn_mongoDB() {
		mongoClient.close();
	}
	public static void open_connexion_BD() throws SQLException {
		String URL = Static_BD.mysql_host;
		Connexion_BD.com = DriverManager.getConnection(URL,Static_BD.mysql_user,Static_BD.mysql_password);
	}
	
	public static void close_connexion_BD() throws SQLException {
		Connexion_BD.com.close();
	}
	
	

}
