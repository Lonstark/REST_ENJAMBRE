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
import crm.enjambre.model.Empresa;
import crm.enjambre.service.IEmpresaService;

@RestController
@RequestMapping("/api/empresa")
@CrossOrigin("*")
public class EmpresaController {
	@Autowired
	private IEmpresaService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Empresa> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron empresas.");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<Empresa> lista = service.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron empresas activas.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Empresa empresa, BindingResult result) {
		//empresa.setEstado(1);
		Empresa nuevoEmpresa = service.registrar(empresa);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEmpresa);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		Empresa empresa = service.listarPorId(id);
		if (empresa != null) {
			return ResponseEntity.ok(empresa);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: La Empresa con ID " + id + " no fue encontrada.");
		}
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody Empresa empresa,
			BindingResult result) {
		Empresa empresaExistente = service.listarPorId(id);

		empresaExistente.setEstado(2);

		Empresa empresaModificado = service.modificar(empresaExistente);
		return ResponseEntity.ok(empresaModificado);
	}

}
