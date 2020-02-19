package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import BD.Static_BD;


public class Test_JDBC2 {
	public static Connection getMySQLConnection() throws SQLException{
		if(Static_BD.mysql_pooling == false) {
			return(DriverManager.getConnection("jdbc:mysql://"+Static_BD.mysql_host+"/"+Static_BD.mysql_bd, Static_BD.mysql_user, Static_BD.mysql_password));
		}else {
			if(database == null) {
				database= new Database("jdbc/db");
			}
			return database.getConnection());
		}
	}

}
