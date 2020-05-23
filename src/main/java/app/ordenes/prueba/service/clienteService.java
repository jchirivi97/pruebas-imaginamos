package app.ordenes.prueba.service;

import app.ordenes.prueba.model.cliente;

public interface clienteService {
	
	
	void saveClient(int cedula,String tipoCC,String nombre,String apellido,String correo,int celular,String direccion,String passw);
	
	cliente getClient(int cedula,String tipocc);
	
	cliente getClient(int cedula,String tipocc,String password);

}
