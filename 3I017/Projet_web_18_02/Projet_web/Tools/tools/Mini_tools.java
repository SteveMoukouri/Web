package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Mini_tools {
	
	public static ArrayList<String> requete(String requeteSQL) throws Exception {
		ArrayList<String> resultat_requete = new ArrayList<String>();
		String URL = "jdbc:mysql://localhost/vene";
		Connection com = DriverManager.getConnection(URL,"root","root");
		String query = requeteSQL;
		Statement st = com.createStatement();
		ResultSet rs = st.executeQuery(query);
		String attribut = attribut_requete(requeteSQL);
		String[] att = attribut.split(",");
		while(rs.next()) {
			for(int i=0; i<att.length ; i++) {
				resultat_requete.add(rs.getString(att[i]));
			}
		}
		rs.close();
		st.close();
		com.close();
		
		return resultat_requete;
	}
	//Dans le cas * , on extrait le nom des tables concernés entre la clause From et Where et 
	//on appelle les fonctions dans les classes tools correspondantes 
	//On cree attribut_requete_full dans chaque tools 
	//A la place de if return impossible si * , appeler la fonction correspondantes entre le frm et le where
	
	public static String attribut_requete(String requeteSQL) {
		String resultat_attribut = "";
		String resultat_table = "";
		String[] z = requeteSQL.split("\\s");
		//on prends le/les attributs entre le Select et le From
		for (int i=0; z[i].compareTo("From") != 0 ; i++) {
			if(z[i].compareTo("Select") != 0) {
				//cas si *, on chercher la/les tables entre le From et le Where, pour pouvoir accèder à tous les attributs des tables
				if(z[i].compareTo("*") == 0) {
					for(int j=i+1; j != z.length && (z[j].compareTo("Where") != 0 || z[j].compareTo(";") != 0); j++) {
						if(z[j].compareTo("From") != 0 && z[j].compareTo(";") != 0 ) {
							resultat_table = resultat_table + z[j];
						}
					}
					// on accèdes aux attributs des table avec getAttributFromTable
					//System.out.println("final = "+resultat_table);
					resultat_attribut = getAttributFromTable(resultat_table);
					return resultat_attribut;
				}
				//cas si != *, on prends le/les attributs entre le Select et le From
				resultat_attribut = resultat_attribut + z[i];
			}
		}
		return resultat_attribut;
	}
	public static String getAttributFromTable(String string_table) {
		String[] table = string_table.split(",");
		String attribut = "";
		for(int i=0 ; i<table.length ; i++) {
			if(table[i].compareTo(User_tools.table) == 0){
				attribut = attribut + User_tools.attributs;
			}
			// A FAIRE COMME LE PREMIER EXEMPLE USER
			
			if(table[i].compareTo("DB_authentification_cai_moukouri") == 0){
				attribut = attribut + tools.Authentification_tools.attribut_authentification();
			}
			if(table[i].compareTo("DB_friends_cai_moukouri") == 0){
				attribut = attribut + tools.Friends_tools.attribut_friends();
			}
		}
		return attribut;
	}
	
	
	public static boolean in_bd(String args, String type_args, String table) throws Exception {
		ArrayList<String>res = requete("Select "+type_args+" From "+table+" ;");
		for(String i :res) {
			if(i.compareTo(args)==0) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean null_vide(String args) {
		return (args == null || args == "");
	}
	
}
