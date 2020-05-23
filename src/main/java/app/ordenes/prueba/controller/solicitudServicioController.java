package app.ordenes.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.ordenes.prueba.model.solicitudServicio;
import app.ordenes.prueba.service.solicitudServicioService;

@RestController
@RequestMapping(value="/solicitudServ")
public class solicitudServicioController {

	
	@Autowired
	solicitudServicioService solicitudServ;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveSolicitudServicio(@RequestBody solicitudServicio soliServ) {
		System.out.print(soliServ.getTicket() +","+soliServ.getIdservicio());
		solicitudServ.saveSolicitud(soliServ.getTicket(),soliServ.getIdservicio());		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{ticket}/{servicio}")
	public ResponseEntity<solicitudServicio> getSolicitudServicio(@PathVariable("ticket") int ticket,@PathVariable("servicio") int servicio){
		return ResponseEntity.ok(solicitudServ.getSolicitudServ(ticket, servicio));
	}
}
