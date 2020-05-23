package app.ordenes.prueba.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import app.ordenes.prueba.model.tecnico;
import app.ordenes.prueba.persistence.tecnicoPersistence;
import app.ordenes.prueba.service.tecnicoServices;

@Service
public class tecnicoServiceImp implements tecnicoServices {

	tecnicoPersistence tecnicoP;

	private void conexion() {

		tecnicoP = new tecnicoPersistence();
	}

	@Override
	public void saveTecnico(int cedula, String tipoCC, String nombre, String apellido, String correo, int celular,
			String passw) {
		conexion();
		tecnicoP.saveTecnico(cedula, tipoCC, nombre, apellido, correo, celular, passw);
	}

	@Override
	public tecnico getTecnico(int cedula, String tipocc) {
		return tecnicoP.getTecnico(cedula, tipocc);
	}

	@Override
	public List<tecnico> getAllTecnico() {
		conexion();
		return tecnicoP.getAllTecnico();
	}

	@Override
	public tecnico getTecnicoTicket(int ticket) {
		conexion();
		return tecnicoP.getTecnicoTicket(ticket);
	}

}
