package app.ordenes.prueba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import app.ordenes.prueba.model.servicio;
import app.ordenes.prueba.persistence.servicioPersistence;
import app.ordenes.prueba.service.servicioService;

@Service
public class servicioServicesImp implements servicioService {

	servicioPersistence servicioP;
	
	private void conexion() {
		servicioP = new servicioPersistence();
	}
	
	@Override
	public void saveSolicitud(int idservicio, String nombre, long valor,int calificacion) {
		conexion();
		servicioP.saveServicio(idservicio, nombre, valor,calificacion);
	}

	@Override
	public servicio getSolicitud(int idservicio) {
		conexion();
		return servicioP.getServicio(idservicio);
	}

	@Override
	public List<servicio> getAllServicio() {
		conexion();
		return  servicioP.getAllServicio();
	}

	@Override
	public List<servicio> getServicioSolicitud(int ticket) {
		conexion();
		return servicioP.getServicioSolicitud(ticket);
	}

	@Override
	public void updateServicio(int idservicio, int calificacion) {
		conexion();
		servicioP.updateServicio(idservicio, calificacion);		
	}

}
