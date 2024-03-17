package crm.enjambre.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.enjambre.model.Ubigeo;
import crm.enjambre.service.IUbigeoService;

@RestController
@RequestMapping("/api/ubigeo")
@CrossOrigin("*")
public class UbigeoController {

	@Autowired
	private IUbigeoService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Ubigeo> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron ubigeos.");
		}
		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/departamentos")
	public ResponseEntity<Object> getDepartamentos() {
		List<String> departamentos = service.getDepartamentos();
		if (departamentos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron Departamentos.");
		}
		List<Map<String, String>> lista = departamentos.stream()
	            .map(departamento -> {
	                Map<String, String> departamentoMap = new HashMap<>();
	                departamentoMap.put("departamento", departamento);
	                return departamentoMap;
	            })
	            .collect(Collectors.toList());
		return ResponseEntity.ok(lista);
	}
	
	
	@GetMapping("/provincias/{departamento}")
	public ResponseEntity<Object> getProvincias(@PathVariable String departamento) {
		List<String> provincias = service.getProvincias(departamento);
        if (provincias.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron Provincias.");
        }
        
        List<Map<String, String>> lista = provincias.stream()
            .map(provincia -> {
                Map<String, String> provinciaMap = new HashMap<>();
                provinciaMap.put("provincia", provincia);
                return provinciaMap;
            })
            .collect(Collectors.toList());

		return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/distritos/{provincias}")
	public ResponseEntity<Object> getDistritos(@PathVariable String provincias) {
		List<Ubigeo> distritos = service.getDistritos(provincias);
		if (distritos.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron Distritos.");
		}
		return ResponseEntity.ok(distritos);
	}

	
	
}
