package app.ordenes.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.ordenes.prueba.model.tecnicoSolicitud;
import app.ordenes.prueba.service.tecnicoSolicitudService;

@RestController
@RequestMapping(value="/tenicoSol")
public class tecnicoSolicitudController {

	@Autowired
	tecnicoSolicitudService tecnicoSolSer;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveTecnicoSolicitud(@RequestBody tecnicoSolicitud tecnicoSol) {
		tecnicoSolSer.saveTenicoSolicitud(tecnicoSol.getToken(),tecnicoSol.getTicket(),tecnicoSol.getTenico(),tecnicoSol.getTipocc());
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{token}")
	public ResponseEntity<tecnicoSolicitud> getTecnicoSolicitud(@PathVariable("token") int token){
		return ResponseEntity.ok(tecnicoSolSer.getTenicoSolicitud(token));
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/all")
	public ResponseEntity<List<tecnicoSolicitud>> getAllTecnicoSolicitud(){
		return ResponseEntity.ok(tecnicoSolSer.getAllTenicoSolicitud());
	}
}
