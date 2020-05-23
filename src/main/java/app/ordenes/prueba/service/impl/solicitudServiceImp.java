package app.ordenes.prueba.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import app.ordenes.prueba.model.solicitud;
import app.ordenes.prueba.persistence.solicitudPersistence;
import app.ordenes.prueba.service.solicitudService;

@Service
public class solicitudServiceImp implements solicitudService {

	solicitudPersistence solicitudP;
	
	private void conexion() {
		solicitudP = new solicitudPersistence();
	}
	
	
	@Override
	public void saveSolicitud(int ticket, Date fecha, String estado, int cedula, String tipocc) {
		conexion();
		solicitudP.saveSolicitud(ticket, fecha, estado, cedula, tipocc);		
	}

	@Override
	public solicitud getSolucitud(int ticket) {
		conexion();
		return solicitudP.getSolicitud(ticket);
	}


	@Override
	public List<solicitud> getSolicitud() {
		conexion();
		return solicitudP.getAllSolicitud();
	}

	
}
