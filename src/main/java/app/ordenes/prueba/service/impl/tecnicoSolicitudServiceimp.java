package app.ordenes.prueba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import app.ordenes.prueba.model.tecnicoSolicitud;
import app.ordenes.prueba.persistence.tenicoSolicitudPersistence;
import app.ordenes.prueba.service.tecnicoSolicitudService;

@Service
public class tecnicoSolicitudServiceimp implements tecnicoSolicitudService{
	
	tenicoSolicitudPersistence tenicoSolic;	
	
	private void conexion() {
		tenicoSolic = new tenicoSolicitudPersistence();
	}

	@Override
	public tecnicoSolicitud getTenicoSolicitud(int token) {
		conexion();
		return tenicoSolic.getTenicoSolicitud(token);
	}

	@Override
	public void saveTenicoSolicitud(int token, int ticket, int tecnico, String tipocc) {
		conexion();
		tenicoSolic.saveTenicoSolicitud(token, ticket, tecnico, tipocc);
	}

	@Override
	public List<tecnicoSolicitud> getAllTenicoSolicitud() {
		conexion();
		return tenicoSolic.getAllTenicoSolicitud();
	}
	
	
	

}
