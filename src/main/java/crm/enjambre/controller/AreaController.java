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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crm.enjambre.model.Area;
import crm.enjambre.service.IAreaService;

@RestController
@RequestMapping("/api/area")
@CrossOrigin("*")
public class AreaController {
	@Autowired
	private IAreaService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Area> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron areas.");
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<Area> lista = service.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron areas activas.");
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Area area, BindingResult result) {
		//area.setEstado(1);
		Area nuevoArea = service.registrar(area);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArea);
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody Area area) {
		Area areaExistente = service.listarPorId(id);

		areaExistente.setEstado(2);

		Area areaModificada = service.modificar(areaExistente);
		return ResponseEntity.ok(areaModificada);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		Area area = service.listarPorId(id);
		if (area != null) {
			return ResponseEntity.ok(area);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El area con ID " + id + " no fue encontrada.");
		}
	}
}
