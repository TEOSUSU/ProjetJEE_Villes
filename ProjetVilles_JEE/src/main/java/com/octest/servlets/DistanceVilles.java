package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octest.beans.Ville;
import com.octest.dao.DaoFactory;
import com.octest.dao.VilleDao;



@WebServlet("/DistanceVilles")
public class DistanceVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public DistanceVilles() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		VilleDao villeDao = DaoFactory.getInstance().getVilleDao();
		ArrayList<Ville> villes = villeDao.recupererVilleDeFranceTrieesParCodePostal();
		request.setAttribute("villes", villes);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/calculDistance.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		VilleDao villeDao = DaoFactory.getInstance().getVilleDao();
		ArrayList<Ville> villes = villeDao.recupererVilleDeFranceTrieesParCodePostal();
		String villeDepart = request.getParameter("villeDepart");
		String villeArrivee = request.getParameter("villeArrivee");
		request.setAttribute("villeDepart", villeDepart);
		request.setAttribute("villeArrivee", villeArrivee);
		Ville villeDeDepart = new Ville();
		Ville villeDarrivee = new Ville();

		for (Ville current : villes) {
			if (current.getNomCommune().equals(villeDepart)) {
				villeDeDepart = current;
			} else if (current.getNomCommune().equals(villeArrivee)) {
				villeDarrivee = current;
			}
		}
		
		//Calcul de la distance
		int RayonTerrestre = 6378137;
		String a = villeDeDepart.getLatitude();
		String b = villeDarrivee.getLatitude();
		String c = villeDeDepart.getLongitude();
		String d = villeDarrivee.getLongitude();

		double latA = Double.parseDouble(a)*Math.PI/180;
		double latB = Double.parseDouble(b)*Math.PI/180;
		double lonA = Double.parseDouble(c)*Math.PI/180;
		double lonB = Double.parseDouble(d)*Math.PI/180;
		
		double calculLongLat = Math.acos(Math.sin(latA)*Math.sin(latB) + Math.cos(latA)*Math.cos(latB)*Math.cos(lonA - lonB));
		double distanceEnM = RayonTerrestre*calculLongLat;
		double distanceEnKM = Math.round(distanceEnM*100/1000.0)/100;
		
		System.out.println(distanceEnKM);
		
		request.setAttribute("distance", distanceEnKM);
		doGet(request, response);
	}

}