package crm.enjambre.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crm.enjambre.model.Zona;
import crm.enjambre.service.IZonaService;

@RestController
@RequestMapping("/api/zona")
@CrossOrigin("*")
public class ZonaController {

	@Autowired
	private IZonaService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Zona> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron Zonas.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Zona zona, BindingResult result) {
		Zona nuevoZona = service.registrar(zona);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoZona);
	}
}
