package app.ordenes.prueba.service.impl;

import org.springframework.stereotype.Service;

import app.ordenes.prueba.model.cliente;
import app.ordenes.prueba.persistence.clientePersistence;
import app.ordenes.prueba.service.clienteService;

@Service
public class clienteServiceImp implements clienteService {

	clientePersistence clientP;
	
	private void conexion() {
	
		clientP = new clientePersistence();
	}
	
	
	@Override
	public void saveClient(int cedula, String tipoCC, String nombre, String apellido, String correo, int celular,
			String direccion, String passw) {
		conexion();
		clientP.saveCliente(cedula, tipoCC, nombre, apellido, correo, celular, direccion, passw);
		
	}

	@Override
	public cliente getClient(int cedula, String tipocc) {
		conexion();
		return clientP.getCliente(cedula, tipocc);
	}


	@Override
	public cliente getClient(int cedula, String tipocc, String password) {
		conexion();
		return clientP.getCliente(cedula, tipocc,password);
	}

}
