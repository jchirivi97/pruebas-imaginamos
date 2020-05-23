package app.ordenes.prueba.service;

import app.ordenes.prueba.model.solicitudServicio;

public interface solicitudServicioService {
	
	void saveSolicitud(int ticket,int idservicio);
	
	solicitudServicio getSolicitudServ (int ticket,int idservicio);

}
