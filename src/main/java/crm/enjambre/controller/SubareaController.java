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

import crm.enjambre.model.Cliente;
import crm.enjambre.model.SubArea;
import crm.enjambre.service.ISubAreaService;

@RestController
@RequestMapping("/api/subarea")
@CrossOrigin("*")
public class SubareaController {
	@Autowired
	private ISubAreaService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<SubArea> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron subareas.");
		}
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<SubArea> lista = service.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron subareas activas.");
		}
		return ResponseEntity.ok(lista);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody SubArea subarea) {
		//subarea.setEstado(1);
		SubArea nuevaSub = service.registrar(subarea);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevaSub);
	}

	@GetMapping("/findByArea/{idArea}")
	public ResponseEntity<Object> listarPorArea(@PathVariable Integer idArea) {
		List<SubArea> lista = service.findByAreaId(idArea);
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron subáreas para el área con ID " + idArea);
		}
		return ResponseEntity.ok(lista);
	}
	
	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody SubArea subarea, BindingResult result) {
		SubArea subareaExistente = service.listarPorId(id);

		subareaExistente.setEstado(2);

		SubArea subareaModificada = service.modificar(subareaExistente);
		return ResponseEntity.ok(subareaModificada);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		SubArea area = service.listarPorId(id);
		if (area != null) {
			return ResponseEntity.ok(area);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: La subarea con ID " + id + " no fue encontrada.");
		}
	}
}
