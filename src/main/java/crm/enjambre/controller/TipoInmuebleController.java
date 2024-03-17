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
import crm.enjambre.model.TipoInmueble;
import crm.enjambre.service.ITipoInmuebleService;

@RestController
@RequestMapping("/api/tipoInmueble")
@CrossOrigin("*")
public class TipoInmuebleController {

	@Autowired
	private ITipoInmuebleService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<TipoInmueble> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron canales de venta.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoInmueble inmueble, BindingResult result) {
		TipoInmueble nuevoInmueble = service.registrar(inmueble);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoInmueble);
	}
}
