package com.octest.dao;

import java.io.IOException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import com.octest.beans.Meteo;

public class MeteoDaoImpl implements MeteoDao {
	private DaoFactory daoFactory;

	public MeteoDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public Meteo getMeteoByGPS(String latitude, String longitude) {
		String apiKey = "46bf7511a5ef71f1495104e3eb3ed35e";
		String url = "https://api.openweathermap.org/data/2.5/weather?lat=" + latitude + "&lon=" + longitude + "&appid="
				+ apiKey + "&mode=xml&units=metric&lang=fr";

		Meteo meteo = new Meteo();
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new URL(url).openStream());
			doc.getDocumentElement().normalize();

			// On récupère la température
			Element elementTemperature = (Element) doc.getElementsByTagName("temperature").item(0);
			String temperature = elementTemperature.getAttribute("value");
			meteo.setTemperature(temperature);

			Element elementWeather = (Element) doc.getElementsByTagName("weather").item(0);
			String temps = elementWeather.getAttribute("value");
			meteo.setTemps(temps);

		} catch (IOException | ParserConfigurationException | SAXException e) {
			e.printStackTrace();
			System.out.println("Problème dans le DAOImpl Récupére météo by GPS");
		}

		return meteo;
	}
}