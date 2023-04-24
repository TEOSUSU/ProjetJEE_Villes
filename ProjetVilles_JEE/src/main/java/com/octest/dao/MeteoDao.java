package com.octest.dao;

import com.octest.beans.Meteo;

public interface MeteoDao {
	
	public Meteo getMeteoByGPS(String latitude, String longitude);
	
}