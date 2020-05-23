package app.ordenes.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.ordenes.prueba.model.solicitud;
import app.ordenes.prueba.service.solicitudService;

@RestController
@RequestMapping(value="/solicitud")
public class solicitudController {

	@Autowired
	solicitudService solicitudServ;
	
	@RequestMapping(method = RequestMethod.POST)
	public void saveSolicitud(@RequestBody solicitud solic) {
		System.out.print(solic.getTicket()+","+solic.getFecha()+","+solic.getEstado()+","+solic.getCedula()+","+solic.getTipocc());
		solicitudServ.saveSolicitud(solic.getTicket(),solic.getFecha() ,solic.getEstado(),solic.getCedula(),solic.getTipocc());
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{ticket}")
	public ResponseEntity<solicitud> getSolicitud(@PathVariable("ticket") int ticket){
		solicitud solic = solicitudServ.getSolucitud(ticket);
		return ResponseEntity.ok(solic);
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/all")
	public ResponseEntity<List<solicitud>> getAllSolicitud(){
		return ResponseEntity.ok(solicitudServ.getSolicitud());
	}
	
}
