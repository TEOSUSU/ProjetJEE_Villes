package com.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Ville;
import com.octest.dao.DaoFactory;
import com.octest.dao.VilleDao;

@WebServlet("/AjoutVille")
public class AjoutVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AjoutVille() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajoutVille.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VilleDao villeDao = DaoFactory.getInstance().getVilleDao();
		String function = request.getParameter("function");
		
		if (function.equals("Ajouter")) {
    		Ville ville = new Ville();
    		ville.setCodeCommune(request.getParameter("Code_Commune"));
			ville.setCodePostal(request.getParameter("Code_Postal"));
			ville.setLatitude(request.getParameter("Latitude"));
			ville.setLibelleAcheminement(request.getParameter("Libelle"));
			ville.setLigne(request.getParameter("Ligne"));
			ville.setLongitude(request.getParameter("Longitude"));
			ville.setNomCommune(request.getParameter("Nom_Commune"));

    		villeDao.inputNewVille(ville);
    		request.setAttribute("valide", "La ville a bien été ajoutée !");
    	}
		doGet(request, response);
	}

}