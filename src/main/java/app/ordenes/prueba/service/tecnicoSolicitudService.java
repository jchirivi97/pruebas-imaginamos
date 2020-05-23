package app.ordenes.prueba.service;

import java.util.List;

import app.ordenes.prueba.model.tecnicoSolicitud;

public interface tecnicoSolicitudService {
	
	tecnicoSolicitud getTenicoSolicitud (int token);

	void saveTenicoSolicitud(int token,int ticket,int tecnico,String tipocc);
	
	List<tecnicoSolicitud> getAllTenicoSolicitud ();
}
