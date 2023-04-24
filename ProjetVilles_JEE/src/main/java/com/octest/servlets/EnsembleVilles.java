package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Meteo;
import com.octest.beans.Ville;
import com.octest.dao.DaoFactory;
import com.octest.dao.MeteoDao;
import com.octest.dao.VilleDao;

@WebServlet("/EnsembleVilles")
public class EnsembleVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EnsembleVilles() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		VilleDao villeDao = DaoFactory.getInstance().getVilleDao();
		MeteoDao meteoDao = DaoFactory.getInstance().getMeteoDao();
		ArrayList<Ville> villes = villeDao.recupererVilleDeFranceTrieesParCodePostal();
		ArrayList<Meteo> meteos = new ArrayList<Meteo>(); 
		request.setAttribute("villes", villes);
		//je parcours toutes les villes pour afficher la météo de chacune d'elle
		for(int i=0; i<villes.size(); i++) {
			Meteo meteo = meteoDao.getMeteoByGPS(villes.get(i).getLatitude(), villes.get(i).getLongitude());
			meteos.add(meteo);
		}
		request.setAttribute("meteos", meteos);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ensembleVilles.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		VilleDao villeDao = DaoFactory.getInstance().getVilleDao();
		String function = request.getParameter("function");
		if (function.equals("Editer")) {
			String idCommune = request.getParameter("idCommune");
    		Ville ville = villeDao.getVilleByCodeCommune(idCommune);
    		request.setAttribute("ville", ville);
    	} else if (function.equals("Supprimer")){
    		String codePostal = request.getParameter("codePostal");
    		String idCommune = request.getParameter("idCommune");
    		villeDao.supprimerVilleByCodeCommune(codePostal, idCommune);
    	} else if (function.equals("Valider")) {
    		// Ici je supprime la ville puis je la recréer
    		// Plutôt que d'update la ville à voir si on laisse 
    		/*String idCommune = request.getParameter("idCommune");	
			villeDao.supprimerVilleByCodeCommune(idCommune);
    		
    		Ville ville = new Ville();
    		ville.setCodeCommune(request.getParameter("Code_Commune"));
			ville.setCodePostal(request.getParameter("Code_Postal"));
			ville.setLatitude(request.getParameter("Latitude"));
			ville.setLibelleAcheminement(request.getParameter("Libelle"));
			ville.setLigne(request.getParameter("Ligne"));
			ville.setLongitude(request.getParameter("Longitude"));
			ville.setNomCommune(request.getParameter("Nom_Commune"));

    		villeDao.inputNewVille(ville);
    		request.setAttribute("ville", null);*/
    	}
		doGet(request, response);
	}

}