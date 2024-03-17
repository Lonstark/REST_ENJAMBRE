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

import crm.enjambre.model.Certificado;
import crm.enjambre.model.Cliente;
import crm.enjambre.service.IClienteService;

@RestController
@RequestMapping("/api/cliente")
@CrossOrigin("*")
public class ClienteController {
	@Autowired
	private IClienteService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Cliente> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron clientes.");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<Cliente> lista = service.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron clientes activos.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Cliente cliente, BindingResult result) {
		//cliente.setEstado(1);
		Cliente nuevoCliente = service.registrar(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody Cliente cliente,
			BindingResult result) {
		Cliente clienteExistente = service.listarPorId(id);

		clienteExistente.setEstado(2);

		Cliente clienteModificado = service.modificar(clienteExistente);
		return ResponseEntity.ok(clienteModificado);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		Cliente area = service.listarPorId(id);
		if (area != null) {
			return ResponseEntity.ok(area);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El cliente con ID " + id + " no fue encontrada.");
		}
	}
}
