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
import crm.enjambre.model.TipoVenta;
import crm.enjambre.service.ITipoVentaService;

@RestController
@RequestMapping("/api/tipoVenta")
@CrossOrigin("*")
public class TipoVentaController {
	
	@Autowired
	private ITipoVentaService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<TipoVenta> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron Tipos de venta.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoVenta tipoVenta, BindingResult result) {
		TipoVenta nuevoTipo = service.registrar(tipoVenta);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
	}
}
