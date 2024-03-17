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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.enjambre.model.Accion;
import crm.enjambre.model.Menu;
import crm.enjambre.model.Perfil;
import crm.enjambre.model.PerfilAccion;
import crm.enjambre.model.Submenu;
import crm.enjambre.repository.IMenuRepository;
import crm.enjambre.request.PerfilRequest;
import crm.enjambre.service.IAccionService;
import crm.enjambre.service.IPerfilAccionService;
import crm.enjambre.service.IPerfilService;
import crm.enjambre.service.ISubmenuService;

@RestController
@RequestMapping("/api/perfil-accion")
@CrossOrigin("*")
public class PerfilAccionController {

	@Autowired
	private IPerfilAccionService servicePerfilAccion;
	
	@Autowired
	private IPerfilService servicePerfil;
	
	@Autowired
	private IAccionService serviceAccion;
	
	@Autowired
	private ISubmenuService serviceSubmenu;
	
	@Autowired
	private IMenuRepository serviceMenu;
	
	@GetMapping
	public ResponseEntity<Object> listarAll() {
		List<PerfilAccion> lista = servicePerfilAccion.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron registros.");
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/menu/{idPerfil}")
	public ResponseEntity<Object> listarMenuByIdPerfil(@PathVariable Integer idPerfil) {
		List<Menu> listar = servicePerfilAccion.findDistinctMenuByPerfilId(idPerfil);
		if (listar.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron Menus para el Perfil con ID: " + idPerfil);
		}
		return ResponseEntity.ok(listar);
	}
	
	@GetMapping("/submenu/{idPerfil}/{idMenu}")
	public ResponseEntity<Object> listarMenuByIdPerfilAndIdMenu(@PathVariable Integer idPerfil, @PathVariable Integer idMenu) {
		List<Submenu> listar = servicePerfilAccion.findDistinctSubMenuByPerfilIdAndMenuId(idPerfil, idMenu);
		if (listar.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron SubMenus para el Perfil con ID: " + idPerfil);
		}
		return ResponseEntity.ok(listar);
	}
	
	@GetMapping("/acciones/{idPerfil}/{idSubmenu}")
	public ResponseEntity<Object> listarAccionesByIdPerfilAndIdSubMenu(@PathVariable Integer idPerfil, @PathVariable Integer idSubmenu) {
		List<Accion> listar = servicePerfilAccion.findDistinctAccionesByPerfilIdAndSubMenuId(idPerfil, idSubmenu);
		if (listar.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("No se encontraron Acciones para el Perfil con ID: " + idPerfil);
		}
		return ResponseEntity.ok(listar);
	}
	
	@PostMapping
	public ResponseEntity<Object> registrarPerfilAndAcciones(@RequestBody PerfilRequest perfilRequest,
	        BindingResult result) {
	    if (result.hasErrors()) {
	        return ResponseEntity.badRequest().body("Error en los datos enviados.");
	    }

	    // Verificar si el perfil ya existe
	    Perfil perfilRegistrado;
	    Optional<Perfil> perfilExistente = servicePerfil.findByNombre(perfilRequest.getPerfil().getNombre());
	    if (perfilExistente.isPresent()) {
	        // Si el perfil ya existe, establecer el perfil existente en lugar de registrar uno nuevo
	        perfilRegistrado = perfilExistente.get();
	    } else {
	        // Si el perfil no existe, registrar uno nuevo
	        perfilRequest.getPerfil().setEstado(1);
	        perfilRegistrado = servicePerfil.registrar(perfilRequest.getPerfil());
	    }

	    // Registrar las acciones asociadas al perfil
	    List<PerfilAccion> perfilAccionList = perfilRequest.getPerfilAccion();
	    for (PerfilAccion perfilAccion : perfilAccionList) {
	        // Verificar si la acción ya existe
	        Optional<Accion> accionExistente = serviceAccion.findByNombre(perfilAccion.getAccion().getNombre());
	        Accion accion;
	        if (accionExistente.isPresent()) {
	            accion = accionExistente.get();
	        } else {
	            accion = perfilAccion.getAccion();
	            accion.setEstado(1);
	            accion.setSubmenues(perfilAccion.getAccion().getSubmenues());
	            accion = serviceAccion.registrar(accion);
	        }

	        // Asociar la acción al perfil
	        perfilAccion.setPerfil(perfilRegistrado);
	        perfilAccion.setAccion(accion);
	        servicePerfilAccion.registrar(perfilAccion);
	    }

	    return ResponseEntity.status(HttpStatus.CREATED).body(perfilRegistrado);
	}





	
}
