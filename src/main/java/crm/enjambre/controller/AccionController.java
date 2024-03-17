package crm.enjambre.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crm.enjambre.model.Accion;
import crm.enjambre.service.IAccionService;

@RestController
@RequestMapping("/api/accion")
@CrossOrigin("*")
public class AccionController {

	@Autowired
	private IAccionService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Accion> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron acciones.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Accion accion, BindingResult result) {
		accion.setEstado(1);
		Accion nuevoAccion = service.registrar(accion);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAccion);
	}

	
}
/*

@GetMapping("/submenu/{idSubMenu}")
	public ResponseEntity<Object> listarAccionesPorSubMenuId(@PathVariable Integer idSubMenu) {
		List<Accion> acciones = service.findAccionesBySubMenuId(idSubMenu);
		if (acciones.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron acciones para el submenú con ID: " + idSubMenu);
		}
		return ResponseEntity.ok(acciones);
	}

*/
