package crm.enjambre.controller;

import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import crm.enjambre.model.Archivo;
import crm.enjambre.model.Venta;
import crm.enjambre.response.ResponseArchivo;
import crm.enjambre.service.IArchivoService;
import crm.enjambre.service.IVentaService;

@RestController
@RequestMapping("/api/ventas")
@CrossOrigin("*")
public class VentaController {

	@Autowired
	private IVentaService ven_service;

	@Autowired
	private IArchivoService ar_service;

	@GetMapping("/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable UUID id) throws FileNotFoundException {
		Archivo file = ar_service.getFileXdownload(id).get();
		return ResponseEntity.status(HttpStatus.OK).header(HttpHeaders.CONTENT_TYPE, file.getType())
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
				.body(file.getData());
	}
	
	@GetMapping("/files")
	public ResponseEntity<List<ResponseArchivo>> getFiles(){
		List<ResponseArchivo> files = ar_service.getAllFiles();
		return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> registrar(@RequestParam("idUsuario") String idUsuario,
			@RequestParam("idCertificado") Integer idCertificado, @RequestParam("idZona") Integer idZona,
			@RequestParam("agendada") boolean agendada, @RequestParam("tipoVenta") String tipoVenta,
			@RequestParam("nombre") String nombre, @RequestParam("apePat") String apePat,
			@RequestParam("apeMat") String apeMat, @RequestParam("idUbigeo") Integer idUbigeo,
			@RequestParam("direccion") String direccion, @RequestParam("coordenadas") String coordenadas,
			@RequestParam("referencia") String referencia, @RequestParam("idTipoDoc") Integer idTipoDoc,
			@RequestParam("documento") String documento, @RequestParam("nroUno") String nroUno,
			@RequestParam("nroDos") String nroDos, @RequestParam("nroTres") String nroTres,
			@RequestParam("whatsapp") String whatsapp, @RequestParam("email") String email,
			@RequestParam("fechaProgramada") String fechaProgramada,
			@RequestParam("horProgramada") LocalTime horProgramada, @RequestParam("canalVenta") Integer canalVenta,
			@RequestParam("tipoInmueble") String tipoInmueble, @RequestParam("idPlan") Integer idPlan,
			@RequestParam("cuotasInstal") Integer cuotasInstal, @RequestParam("cantMesh") Integer cantMesh,
			@RequestParam("winbox") String winbox, @RequestParam("nroPortar") String nroPortar,
			@RequestParam("relacionPrecio") String relacioPrecio, @RequestParam("obs") String obs,
			@RequestParam("imgScore") MultipartFile imgScore, @RequestParam("imgDNIuno") MultipartFile imgDNIuno,
			@RequestParam("imgDNIdos") MultipartFile imgDNIdos, @RequestParam("grabacion") MultipartFile grabacion) {

		ven_service.guardarVenta(idUsuario, idCertificado, idZona, agendada, tipoVenta, nombre, apePat, apeMat,
				idUbigeo, direccion, coordenadas, referencia, idTipoDoc, documento, nroUno, nroDos, nroTres, whatsapp,
				email, fechaProgramada, horProgramada, canalVenta, tipoInmueble, idPlan, cuotasInstal, cantMesh, winbox,
				nroPortar, relacioPrecio, obs, imgScore, imgDNIuno, imgDNIdos, grabacion);

		return ResponseEntity.status(HttpStatus.CREATED).body("Se registró la venta con éxito");
	}
	
	@GetMapping("/listar")
	public ResponseEntity<List<Venta>> listarVentas(){
		List<Venta> ventas = ven_service.listarVentas();
		return ResponseEntity.status(HttpStatus.OK).body(ventas);
	}
	

}
