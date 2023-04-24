package com.octest.dao;

import java.util.ArrayList;

import com.octest.beans.Ville;

public interface VilleDao {

	ArrayList<Ville> recupererVilleDeFranceTrieesParCodePostal();
	Ville getVilleByCodeCommune(String codeCommuneINSEE);
	void supprimerVilleByCodeCommune(String codePostal, String codeCommuneINSEE);
	void inputNewVille(Ville ville);
}