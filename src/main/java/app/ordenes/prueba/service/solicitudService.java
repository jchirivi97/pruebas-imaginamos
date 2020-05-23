package app.ordenes.prueba.service;

import java.sql.Date;
import java.util.List;

import app.ordenes.prueba.model.solicitud;

public interface solicitudService {

	void saveSolicitud(int ticket,Date fecha,String estado,int cedula,String tipocc);
	
	solicitud getSolucitud(int ticket);
	
	List<solicitud> getSolicitud ();
}
