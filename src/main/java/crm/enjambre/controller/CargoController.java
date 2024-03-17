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
import crm.enjambre.model.Cargo;
import crm.enjambre.service.ICargoService;

@RestController
@RequestMapping("/api/cargo")
@CrossOrigin("*")
public class CargoController {

	@Autowired
	private ICargoService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Cargo> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron cargos.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Cargo cargo, BindingResult result) {
		cargo.setEstado(1);
		Cargo nuevoCargo = service.registrar(cargo);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCargo);
	}

}
