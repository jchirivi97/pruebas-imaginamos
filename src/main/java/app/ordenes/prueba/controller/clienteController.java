package app.ordenes.prueba.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.ordenes.prueba.model.cliente;
import app.ordenes.prueba.service.clienteService;

@RestController
@RequestMapping(value="/cliente")
public class clienteController {
	
	@Autowired
	clienteService clientServ;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public void addClient(@RequestBody cliente client) {
		clientServ.saveClient(client.getCedula(),client.getTipoCC(),client.getNombre(),client.getApellido(),client.getCorreo(),client.getCelular(),client.getDireccion(),client.getPassw());		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{cedula}/{tipocc}")
	public ResponseEntity<cliente> getClient(@PathVariable("cedula") int cedula,@PathVariable("tipocc") String tipocc){
		cliente client = clientServ.getClient(cedula, tipocc); 
		return ResponseEntity.ok(client);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{cedula}/{tipocc}/{password}")
	public ResponseEntity<cliente> getClient(@PathVariable("cedula") int cedula,@PathVariable("tipocc") String tipocc,@PathVariable("password") String password){
		cliente client = clientServ.getClient(cedula, tipocc,password); 
		return ResponseEntity.ok(client);
		
	}

}
