package crm.enjambre.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crm.enjambre.model.Usuario;
import crm.enjambre.service.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

	@Autowired
	private IUsuarioService serviceUsuario;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Usuario> lista = serviceUsuario.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios.");
		}
		return ResponseEntity.ok(lista);
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody Usuario usuario,
			BindingResult result) {
		Usuario usuarioExistente = serviceUsuario.listarPorId(id);

		usuarioExistente.setEstado(2);

		Usuario usuarioModificada = serviceUsuario.modificar(usuarioExistente);
		return ResponseEntity.ok(usuarioModificada);
	}

	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<Usuario> lista = serviceUsuario.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron usuarios activos.");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorCodigo(@PathVariable("codigo") String id) {
		Optional<Usuario> user = this.serviceUsuario.findByIdUsuario(id);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El usurio con ID " + id + " no fue encontrado.");
		}
	}

}
