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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crm.enjambre.model.Submenu;
import crm.enjambre.service.ISubmenuService;

@RestController
@RequestMapping("/api/submenu")
@CrossOrigin("*")
public class SubmenuController {

	@Autowired
	private ISubmenuService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Submenu> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron menus.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Submenu submenu, BindingResult result) {
		submenu.setEstado(1);
		Submenu nuevoSubmenu = service.registrar(submenu);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSubmenu);
	}

	
}

/*
@GetMapping("/findByMenuId/{menuId}")
public ResponseEntity<Object> listarPorMenuId(@PathVariable Integer menuId) {
	List<Submenu> lista = service.findByMenuId(menuId);
	if (lista.isEmpty()) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("No se encontraron submenús para el menú con ID " + menuId);
	}
	return ResponseEntity.ok(lista);
}
*/