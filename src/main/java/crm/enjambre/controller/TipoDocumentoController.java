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
import crm.enjambre.model.TipoDocumento;
import crm.enjambre.service.ITipoDocumentoService;

@RestController
@RequestMapping("/api/documento")
@CrossOrigin("*")
public class TipoDocumentoController {
	@Autowired
	private ITipoDocumentoService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<TipoDocumento> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron documentos.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody TipoDocumento documento, BindingResult result) {
		TipoDocumento nuevoDocumento = service.registrar(documento);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoDocumento);
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody TipoDocumento documento,
			BindingResult result) {
		TipoDocumento docExistente = service.listarPorId(id);

		docExistente.setEstado(2);

		TipoDocumento docModificado = service.modificar(docExistente);
		return ResponseEntity.ok(docModificado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		TipoDocumento area = service.listarPorId(id);
		if (area != null) {
			return ResponseEntity.ok(area);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El documento con ID " + id + " no fue encontrada.");
		}
	}
}
