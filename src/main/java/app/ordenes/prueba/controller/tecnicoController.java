package app.ordenes.prueba.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.ordenes.prueba.model.tecnico;
import app.ordenes.prueba.service.tecnicoServices;

@RestController
@RequestMapping(value="/tecnico")
public class tecnicoController {
	
	@Autowired
	tecnicoServices tecnicoServ;

	@RequestMapping(method = RequestMethod.POST)
	public void addtecnico(@RequestBody tecnico tecnico) {
		tecnicoServ.saveTecnico(tecnico.getCedula(),tecnico.getTipoCC(),tecnico.getNombre(),tecnico.getApellido(),tecnico.getCorreo(),tecnico.getCelular(),tecnico.getPassw());		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{cedula}/{tipocc}")
	public ResponseEntity<tecnico> getTecnico(@PathVariable("cedula") int cedula,@PathVariable("tipocc") String tipocc){
		tecnico tecnico = tecnicoServ.getTecnico(cedula, tipocc); 
		return ResponseEntity.ok(tecnico);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/{cedula}/{tipocc}/{passw}")
	public ResponseEntity<tecnico> getTecnicoLogin(@PathVariable("cedula") int cedula,@PathVariable("tipocc") String tipocc,@PathVariable("passw") String passw){
		tecnico tecnico = tecnicoServ.getTecnicoLogin(cedula, tipocc, passw);
		return ResponseEntity.ok(tecnico);
		
	}
	
	@RequestMapping(method = RequestMethod.GET,path="/all")
	public ResponseEntity<List<tecnico>> getAllTecnico(){
		return ResponseEntity.ok(tecnicoServ.getAllTecnico());
		
	}

	@RequestMapping(method = RequestMethod.GET,path="/{ticket}")
	public ResponseEntity<tecnico> getTecnicoSolcitud(@PathVariable("ticket") int ticket){
		tecnico tecnico = tecnicoServ.getTecnicoTicket(ticket); 
		return ResponseEntity.ok(tecnico);
		
	}
	
	
}
