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
import crm.enjambre.model.CanalVenta;
import crm.enjambre.service.ICanalVentaService;

@RestController
@RequestMapping("/api/canalVenta")
@CrossOrigin("*")
public class CanalVentaController {

	@Autowired
	private ICanalVentaService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<CanalVenta> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron canales de venta.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody CanalVenta canal, BindingResult result) {
		CanalVenta nuevoCanal = service.registrar(canal);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCanal);
	}
}
