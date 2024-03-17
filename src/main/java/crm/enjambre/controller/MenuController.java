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
import crm.enjambre.model.Menu;
import crm.enjambre.service.IMenuService;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin("*")
public class MenuController {
	@Autowired
	private IMenuService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Menu> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron menus.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Menu menu, BindingResult result) {
		menu.setEstado(1);
		Menu nuevoMenu = service.registrar(menu);
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoMenu);
	}
	

}
/*
 	@GetMapping("/perfil/{idPerfil}")
    public ResponseEntity<Object> listarPorPerfil(@PathVariable Integer idPerfil) {
        List<Menu> lista = service.findMenusByPerfilId(idPerfil);
        if (lista.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron menús para el perfil con ID " + idPerfil);
        }
        return ResponseEntity.ok(lista);
    }
*/

