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

import crm.enjambre.model.TipoPlan;
import crm.enjambre.service.ITipoPlanService;

@RestController
@RequestMapping("/api/tipoPlan")
@CrossOrigin("*")
public class TipoPlanController {

	@Autowired
	private ITipoPlanService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<TipoPlan> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron tipos de planes.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoPlan tplan, BindingResult result) {
		TipoPlan nuevoTplan = service.registrar(tplan);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTplan);
	}
}
