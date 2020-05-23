package app.ordenes.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.ordenes.prueba.model.servicio;
import app.ordenes.prueba.service.servicioService;

@RestController
@RequestMapping(value="/servicio")
public class servicioController {


	@Autowired
	servicioService servicioSev;
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveService(servicio service) {
		servicioSev.saveSolicitud(service.getIdservicio(),service.getNombre(),service.getValor(),service.getCalificacion());
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{id}")
	public ResponseEntity<servicio> getServicio(@PathVariable("id") int id){
		return ResponseEntity.ok(servicioSev.getSolicitud(id));
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/all")
	public ResponseEntity<List<servicio>> getServicio(){
		return ResponseEntity.ok(servicioSev.getAllServicio());
	}
	
}
