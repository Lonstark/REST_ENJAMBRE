package crm.enjambre.controller;

import java.util.List;
import java.util.Optional;
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
import crm.enjambre.model.Perfil;
import crm.enjambre.request.PerfilRequest;
import crm.enjambre.service.IPerfilService;

@RestController
@RequestMapping("/api/perfil")
@CrossOrigin("*")
public class PerfilController {

	@Autowired
	private IPerfilService perfilService;

	
	@GetMapping
	public ResponseEntity<Object> listarPerfiles() {
		List<Perfil> lista = perfilService.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron perfiles.");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<Perfil> lista = perfilService.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron perfiles activos.");
		}
		return ResponseEntity.ok(lista);
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody Perfil perfil,
			BindingResult result) {
		Perfil perfilExistente = perfilService.listarPorId(id);

		perfilExistente.setEstado(2);

		Perfil perfilModificada = perfilService.modificar(perfilExistente);
		return ResponseEntity.ok(perfilModificada);
	}
	
	

	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		Perfil area = perfilService.listarPorId(id);
		if (area != null) {
			return ResponseEntity.ok(area);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El perfil con ID " + id + " no fue encontrada.");
		}
	}
}





/*

	@PostMapping
	public ResponseEntity<Object> registrarPerfilYAcciones(@RequestBody PerfilRequest perfilRequest,
			BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Error en los datos enviados.");
		}

		// Verificar si el perfil ya existe
		Perfil perfilRegistrado;
		Optional<Perfil> perfilExistente = perfilService.findByNombre(perfilRequest.getPerfil().getNombre());
		if (perfilExistente.isPresent()) {
			// Si el perfil ya existe, establecer el perfil existente en lugar de registrar
			// uno nuevo
			perfilRegistrado = perfilExistente.get();
		} else {
			// Si el perfil no existe, registrar uno nuevo
			perfilRequest.getPerfil().setEstado(1);
			perfilRegistrado = perfilService.registrar(perfilRequest.getPerfil());
		}

		// Registrar las acciones asociadas al perfil
		List<PerfilSubMenuAccion> perfilSubMenuAccionList = perfilRequest.getPerfilSubMenuAccion();
		for (PerfilSubMenuAccion perfilSubMenuAccion : perfilSubMenuAccionList) {
			perfilSubMenuAccion.setPerfil(perfilRegistrado); // Establecer el perfil para cada acción
			perfilSubMenuAccionService.registrar(perfilSubMenuAccion);
		}

		return ResponseEntity.status(HttpStatus.CREATED).body(perfilRegistrado);
	}

*/

