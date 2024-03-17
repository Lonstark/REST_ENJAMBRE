package crm.enjambre.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import crm.enjambre.model.Venta;

public interface IVentaService extends ICRUD<Venta, Integer> {

	Venta guardarVenta(String idUsuario, Integer idCertificado, Integer idZona, boolean agendada, String tipoVenta, 
			String nombre, String apePat, String apeMat, Integer idUbigeo, String direccion, String coordenadas,
			String referencia, Integer idTipoDoc, String documento, String nroUno, String nroDos, String nroTres,
			String whatsapp, String email, String fechaProgramada, LocalTime horProgramada, Integer canalVenta, String tipoInmueble,
			Integer idPlan, Integer cuotasInstal, Integer cantMesh, String winbox, String nroPortar,
			String relacioPrecio, String obs, MultipartFile imgScore, MultipartFile imgDNIuno, MultipartFile imgDNIdos, MultipartFile grabacion);
	
	
	List<Venta> listarVentas();
		
}
