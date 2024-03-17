package crm.enjambre.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import crm.enjambre.model.Archivo;
import crm.enjambre.model.CanalVenta;
import crm.enjambre.model.Certificado;
import crm.enjambre.model.Plan;
import crm.enjambre.model.TipoDocumento;
import crm.enjambre.model.Ubigeo;
import crm.enjambre.model.Usuario;
import crm.enjambre.model.Venta;
import crm.enjambre.model.Zona;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IVentaRepository;
import crm.enjambre.service.IArchivoService;
import crm.enjambre.service.IVentaService;

@Service
@Transactional
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {

	@Autowired
	private IVentaRepository repo;

	@Autowired
	private IArchivoService service;

	@Override
	protected IGenericRepo<Venta, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Venta guardarVenta(String idUsuario, Integer idCertificado, Integer idZona, boolean agendada,
			String tipoVenta, String nombre, String apePat, String apeMat, Integer idUbigeo, String direccion,
			String coordenadas, String referencia, Integer idTipoDoc, String documento, String nroUno, String nroDos,
			String nroTres, String whatsapp, String email, String fechaProgramada, LocalTime horProgramada,
			Integer canalVenta, String tipoInmueble, Integer idPlan, Integer cuotasInstal, Integer cantMesh,
			String winbox, String nroPortar, String relacioPrecio, String obs, MultipartFile imgScore,
			MultipartFile imgDNIuno, MultipartFile imgDNIdos, MultipartFile grabacion) {

		Usuario user = new Usuario();
		user.setIdUsuario(idUsuario);

		Certificado certif = new Certificado();
		certif.setIdCertificado(idCertificado);

		Zona zon = new Zona();
		zon.setIdZona(idZona);

		Ubigeo ubi = new Ubigeo();
		ubi.setIdUbigeo(idUbigeo);

		TipoDocumento tipoDoc = new TipoDocumento();
		tipoDoc.setIdTipoDocumento(idTipoDoc);

		CanalVenta canalV = new CanalVenta();
		canalV.setIdCanalVenta(canalVenta);

		Plan plan = new Plan();
		plan.setId(idPlan);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		try {
			date = dateFormat.parse(fechaProgramada);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<MultipartFile> archivos = new ArrayList<>(Arrays.asList(imgScore, imgDNIuno, imgDNIdos, grabacion));
		// System.out.println("ARCHIVOS RECHUCHE: " + archivos.size());

		List<Archivo> listaA = new ArrayList<Archivo>();
		
		Venta v = Venta.builder().usuario(user).certificado(certif).zona(zon).agendada(agendada).tipoVenta(tipoVenta)
				.nombre(nombre).apePat(apePat).apeMat(apeMat).ubigeo(ubi).direccion(direccion).coordenadas(coordenadas)
				.referencia(referencia).tipoDoc(tipoDoc).documento(documento).nroUno(nroUno).nroDos(nroDos)
				.nroTres(nroTres).whatsapp(whatsapp).email(email).fecProgramada(date).horaProgramada(horProgramada)
				.canalVenta(canalV).tipoInmueble(tipoInmueble).planContratado(plan).cuotasInstal(cuotasInstal)
				.cantMesh(cantMesh).winbox(winbox).nroPortar(nroPortar).relacionPrecio(relacioPrecio).obs(obs)
				.build();
		
		try {
			listaA = service.convertirList(archivos, v);

		} catch (Exception e) {
			// TODO: handle exception
		}
		v.setVenta(listaA);

		return repo.save(v);
	}

	@Override
	public List<Venta> listarVentas() {
		List<Venta> lista = repo.findAll();
		List<Venta> result = new ArrayList<>();

		for (Venta v : lista) {
			Venta ventaConArchivosActualizados = new Venta();  // Crear una copia de la venta original
	        BeanUtils.copyProperties(v, ventaConArchivosActualizados);
	        
	        List<Archivo> archivosActualizados = new ArrayList<>();
			for (Archivo a : v.getVenta()) {				

				String fileDownload = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/ventas/files/").path(a.getIdArchivo().toString()).toUriString();

				a.setUrl(fileDownload);
				// Crear una copia del archivo y omitir el atributo 'data'
	            Archivo archivoSinData = new Archivo();
	            BeanUtils.copyProperties(a, archivoSinData, "data");
	            archivosActualizados.add(archivoSinData);
			}		
			ventaConArchivosActualizados.setVenta(archivosActualizados);  // Agregar la lista actualizada de archivos a la copia de venta
	        result.add(ventaConArchivosActualizados); 
		}
		return result;
	}

}
