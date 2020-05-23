package app.ordenes.prueba.service;

import java.util.List;

import app.ordenes.prueba.model.tecnico;

public interface tecnicoServices {
	
	void saveTecnico(int cedula,String tipoCC,String nombre,String apellido,String correo,int celular,String passw);
	
	tecnico getTecnico(int cedula,String tipocc);
	
	List<tecnico> getAllTecnico();
	
	tecnico getTecnicoTicket(int ticket);

}
