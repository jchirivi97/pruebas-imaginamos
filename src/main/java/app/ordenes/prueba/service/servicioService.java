package app.ordenes.prueba.service;

import java.util.List;

import app.ordenes.prueba.model.servicio;

public interface servicioService {

	void saveSolicitud(int idservicio,String nombre,long valor,int calificacion);
	
	servicio getSolicitud (int idservicio);
	
	List<servicio> getAllServicio ();
	
	List<servicio> getServicioSolicitud (int ticket);
}
