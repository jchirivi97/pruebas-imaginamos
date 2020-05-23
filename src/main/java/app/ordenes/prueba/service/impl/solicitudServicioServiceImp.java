package app.ordenes.prueba.service.impl;

import org.springframework.stereotype.Service;

import app.ordenes.prueba.model.solicitudServicio;
import app.ordenes.prueba.persistence.solicitudServicioPersistence;
import app.ordenes.prueba.service.solicitudServicioService;

@Service
public class solicitudServicioServiceImp implements solicitudServicioService{
	
	solicitudServicioPersistence solicitud;
	
	private void conexion() {
		solicitud = new solicitudServicioPersistence();
	}
	
	@Override
	public void saveSolicitud(int ticket, int idservicio) {
		conexion();
		solicitud.saveSolicitud(ticket, idservicio);
	}

	@Override
	public solicitudServicio getSolicitudServ(int ticket, int idservicio) {
		conexion();
		return solicitud.getSolicitudServ(ticket, idservicio);
	}
}
