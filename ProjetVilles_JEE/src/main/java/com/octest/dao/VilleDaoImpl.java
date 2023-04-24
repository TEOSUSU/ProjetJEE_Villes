package com.octest.dao;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.octest.beans.Ville;
import com.octest.builder.Constructor;

public class VilleDaoImpl implements VilleDao {
	private DaoFactory daoFactory;

	public VilleDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public ArrayList<Ville> recupererVilleDeFranceTrieesParCodePostal() {
		JSONArray json;
		ArrayList<Ville> listeVilles = new ArrayList<Ville>();
		try {
			json = Constructor.readJsonFromUrl("http://localhost:8181/ville?orderbyByName=yes");

			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonObject = json.getJSONObject(i);
				Ville ville = new Ville();
				ville.setCodeCommune(jsonObject.getString("codeCommune"));
				ville.setCodePostal(jsonObject.getString("codePostal"));
				ville.setLatitude(jsonObject.getString("latitude"));
				ville.setLibelleAcheminement(jsonObject.getString("libelleAcheminement"));
				ville.setLigne(jsonObject.getString("ligne"));
				ville.setLongitude(jsonObject.getString("longitude"));
				ville.setNomCommune(jsonObject.getString("nomCommune"));
				listeVilles.add(ville);
			}
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problème dans le DAOImpl Récupére villes by codePostal");
		}
		return listeVilles;
	}

	@Override
	public Ville getVilleByCodeCommune(String codeCommuneINSEE) {
		JSONArray json;
		Ville ville = new Ville();
		try {
			json = Constructor.readJsonFromUrl("http://localhost:8181/ville?codeCommuneINSEE=" + codeCommuneINSEE);

			for (int i = 0; i < json.length(); i++) {
				JSONObject jsonObject = json.getJSONObject(i);
				ville.setCodeCommune(jsonObject.getString("codeCommune"));
				ville.setCodePostal(jsonObject.getString("codePostal"));
				ville.setLatitude(jsonObject.getString("latitude"));
				ville.setLibelleAcheminement(jsonObject.getString("libelleAcheminement"));
				ville.setLigne(jsonObject.getString("ligne"));
				ville.setLongitude(jsonObject.getString("longitude"));
				ville.setNomCommune(jsonObject.getString("nomCommune"));
			}
		} catch (JSONException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Problème dans le DAOImpl Récupére villes by codePostal");
		}
		return ville;
	}

	@Override
	public void supprimerVilleByCodeCommune(String codePostal, String codeCommuneINSEE) {
		try {
			URL url = new URL("http://localhost:8181/ville?codePostal="+ codePostal +"&codeCommune=" + codeCommuneINSEE);

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("DELETE");
			conn.setRequestProperty("Accept", "application/json");

			int responseCode = conn.getResponseCode();
			if (responseCode != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + responseCode);
			}

			conn.disconnect();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void inputNewVille(Ville ville) {
		try {
			URL url = new URL("http://localhost:8181/ville?codePostal=" + ville.getCodePostal() + "&nomCommune=" + 
					ville.getNomCommune() + "&codeCommune=" + ville.getCodeCommune() + "&libelleAcheminement=" + 
					ville.getLibelleAcheminement() + "&ligne=" + ville.getligne() + "&longitude=" + ville.getLongitude() + 
					"&latitude=" + ville.getLatitude());
			HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
			httpUrlConnection.setRequestMethod("POST");
			httpUrlConnection.setRequestProperty("Accept", "application/json");

			if (httpUrlConnection.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + httpUrlConnection.getResponseCode());
			}

			System.out.println("Output from Serveur ... /n");
			httpUrlConnection.disconnect();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}