package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;
import BD.*;

public class Mini_tools {
	// Recupere le resultat de la requete dans une arrayList de String
	public static ArrayList<String> requeteGET(String requeteSQL) throws Exception {
		ArrayList<String> resultat_requete = new ArrayList<String>();
		Connection com = Connexion_BD.open_connexion_BD();
		Statement st = com.createStatement();
		ResultSet rs = st.executeQuery(requeteSQL);
		String attribut = attribut_requete(requeteSQL);
		String[] att = attribut.split(",");
		while (rs.next()) {
			for (int i = 0; i < att.length; i++) {
				resultat_requete.add(rs.getString(att[i]));
			}
		}
		rs.close();
		st.close();
		Connexion_BD.close_connexion_BD(com);
		return resultat_requete;
	}

	// Dans le cas * , on extrait le nom des tables concernés entre la clause From
	// et Where et
	// on appelle les fonctions dans les classes tools correspondantes
	// On cree attribut_requete_full dans chaque tools
	// A la place de if return impossible si * , appeler la fonction correspondantes
	// entre le frm et le where

	public static String attribut_requete(String requeteSQL) {
		String resultat_attribut = "";
		String resultat_table = "";
		// on separe la requete par des es
		String[] tab_split = requeteSQL.split("\\s");
		// on prends le/les attributs entre le Select et le From
		for (int i = 0; tab_split[i].compareTo("From") != 0; i++) {
			if (tab_split[i].compareTo("Select") != 0) {
				// cas si *, on chercher la/les tables entre le From et le Where, pour pouvoir
				// acceder a tous les attributs des tables
				if (tab_split[i].compareTo("*") == 0) {
					for (int j = i + 1; j != tab_split.length
							&& (tab_split[j].compareTo("Where") != 0 && tab_split[j].compareTo(";") != 0); j++) {
						if (tab_split[j].compareTo("From") != 0 && tab_split[j].compareTo(";") != 0) {
							resultat_table = resultat_table + tab_split[j];
						}
					}
					// on accedes aux attributs des table avec getAttributFromTable
					resultat_attribut = getAttributFromTable(resultat_table);
					return resultat_attribut;
				}
				// cas si different de *, on prends le/les attributs entre le Select et le From
				resultat_attribut = resultat_attribut + tab_split[i];
			}
		}
		return resultat_attribut;
	}

	public static String getAttributFromTable(String string_table) {
		String[] table = string_table.split(",");
		String attribut = "";
		for (int i = 0; i < table.length; i++) {
			if (table[i].compareTo(User_tools.table) == 0) {
				attribut = attribut + User_tools.attributs;
			}
			if (table[i].compareTo(Authentification_tools.table) == 0) {
				attribut = attribut + Authentification_tools.attributs;
			}
			if (table[i].compareTo(Friends_tools.table) == 0) {
				attribut = attribut + Friends_tools.attributs;
			}
		}
		return attribut;
	}

	public static boolean in_bd(String args, String type_args, String table) throws Exception {
		ArrayList<String> res = requeteGET(
				"Select " + type_args + " From " + table + " Where " + type_args + "='" + args + "' ;");
		for (String i : res) {
			if (i.compareTo(args) == 0) {
				return true;
			}
		}
		return false;
	}

	public static boolean null_vide(String args) {
		return (args == null || args == "");
	}

	public static boolean requeteSET(String requeteSQL) throws Exception {
		Connection com = Connexion_BD.open_connexion_BD();
		Statement st = com.createStatement();
		int rs = st.executeUpdate(requeteSQL);
		st.close();
		Connexion_BD.close_connexion_BD(com);
		if (rs != 0) {
			return true;
		}
		return false;

	}

	public static JSONObject creation_json(ArrayList<String> attributs, ArrayList<String> nom_attributs)
			throws JSONException {
		JSONObject json = ErrorJSON.serviceAccepted();
		if (attributs == null && nom_attributs == null) {
			return json;
		}
		if (attributs.size() != nom_attributs.size()) {
			return sous_json(attributs, nom_attributs);
		}
		for (int i = 0; i < attributs.size(); i++) {
			json.put(nom_attributs.get(i % nom_attributs.size()), attributs.get(i));
		}
		return json;
	}

	public static JSONObject sous_json(ArrayList<String> attributs, ArrayList<String> nom_attributs)
			throws JSONException {
		JSONObject list_json = ErrorJSON.serviceAccepted();
		int cpt = 1;
		for (int i = 0; i < attributs.size(); i++) {
			JSONObject json = ErrorJSON.serviceAccepted();
			list_json.accumulate(String.valueOf(cpt),
					json.put(nom_attributs.get(i % nom_attributs.size()), attributs.get(i)));
			if (((i % nom_attributs.size()) - (nom_attributs.size() - 1)) == 0) {
				cpt++;
			}
		}
		return list_json;
	}

	public static ArrayList<String> acces_nom_attributs(String requeteSQL) {
		String attribut = Mini_tools.attribut_requete(requeteSQL);
		ArrayList<String> nom_attributs = new ArrayList<String>();
		String[] att = attribut.split(",");
		for (String i : att) {
			nom_attributs.add(i);
		}
		return nom_attributs;
	}

}
