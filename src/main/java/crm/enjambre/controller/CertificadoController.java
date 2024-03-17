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

import crm.enjambre.model.Area;
import crm.enjambre.model.Certificado;
import crm.enjambre.service.ICertificadoService;

@RestController
@RequestMapping("/api/certificado")
@CrossOrigin("*")
public class CertificadoController {

	@Autowired
	private ICertificadoService service;

	@GetMapping
	public ResponseEntity<Object> listar() {
		List<Certificado> lista = service.listar();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron certificados.");
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping("/listActivos")
	public ResponseEntity<Object> listarAllEstado1() {
		List<Certificado> lista = service.findAllByEstado();
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron certificados activos.");
		}
		return ResponseEntity.ok(lista);
	}

	@PostMapping
	public ResponseEntity<Object> registrar(@RequestBody Certificado certificado, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.badRequest().body("Error en los datos enviados.");
		}

		if (certificado.getCodigo() == null || certificado.getCodigo().isEmpty()) {
	        // Asignar un nuevo código de certificado solo si no se proporciona uno
	        certificado.setCodigo(generarCodigoCertificado());
	    }
		
		Certificado nuevoCertificado = service.registrar(certificado);
		// registrar
		// el certificado
		return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCertificado);
	}

	private String generarCodigoCertificado() {
		Integer ultimoNumeroCertificado = service.obtenerCertificado();
		if (ultimoNumeroCertificado == null) {
			ultimoNumeroCertificado = 0;
		}
		// Generar el próximo número de certificado
		Integer nuevoNumeroCertificado = ultimoNumeroCertificado + 1;
		String formatoNumeroCertificado = String.format("%09d", nuevoNumeroCertificado);
		return "CT-" + formatoNumeroCertificado;
	}

	@PutMapping("/desactivar/{id}")
	public ResponseEntity<Object> desactivar(@PathVariable("id") int id, @RequestBody Certificado certificado,
			BindingResult result) {
		Certificado certificadoExistente = service.listarPorId(id);

		certificadoExistente.setEstado(2);

		Certificado certificadoModificada = service.modificar(certificadoExistente);
		return ResponseEntity.ok(certificadoModificada);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> listarPorId(@PathVariable("id") int id) {
		Certificado area = service.listarPorId(id);
		if (area != null) {
			return ResponseEntity.ok(area);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Error: El certificado con ID " + id + " no fue encontrada.");
		}
	}
}
