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
import crm.enjambre.model.TipoRelacionPredio;
import crm.enjambre.service.ITipoRelacionPredioService;

@RestController
@RequestMapping("/api/tipoPredio")
@CrossOrigin("*")
public class TipoRelacionPredioController {

	@Autowired
	private ITipoRelacionPredioService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<TipoRelacionPredio> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron relaciones predio.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoRelacionPredio trpredio, BindingResult result) {
		TipoRelacionPredio nuevoTrpredio = service.registrar(trpredio);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTrpredio);
	}
}
