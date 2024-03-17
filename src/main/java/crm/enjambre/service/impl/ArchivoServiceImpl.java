package crm.enjambre.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import crm.enjambre.model.Archivo;
import crm.enjambre.model.Venta;
import crm.enjambre.repository.IArchivoRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.response.ResponseArchivo;
import crm.enjambre.service.IArchivoService;

@Service
public class ArchivoServiceImpl extends CRUDImpl<Archivo, UUID> implements IArchivoService {

	@Autowired
	private IArchivoRepository repo;

	@Override
	protected IGenericRepo<Archivo, UUID> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public List<Archivo> convertirList(List<MultipartFile> files, Venta v) throws IOException {
		List<Archivo> lista = new ArrayList<Archivo>();

		for (int i = 0; i < files.size(); i++) {
			MultipartFile ar = files.get(i);
			if (!ar.isEmpty()) {
				String fileName = StringUtils.cleanPath(ar.getOriginalFilename());

				Archivo a = Archivo.builder().descripcionArchivo(i).name(fileName).type(ar.getContentType()).url("")
						.data(ar.getBytes()).fecha(Date.from(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS)
								.atZone(ZoneId.systemDefault()).toInstant())).venta(v)
						.build();

				lista.add(a);
			}
		}
		return lista;
	}

	@Override
	public Optional<Archivo> getFileXdownload(UUID id) throws FileNotFoundException {
		Optional<Archivo> file = repo.findById(id);
		if (file.isPresent()) {
			return file;
		}
		throw new FileNotFoundException();
	}

	@Override
	public List<ResponseArchivo> getAllFiles() {
		List<ResponseArchivo> archivos = repo.findAll().stream().map(dbFile -> {
			String fileDownload = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/ventas/files/")
					.path(dbFile.getIdArchivo().toString()).toUriString();

			return ResponseArchivo.builder().name(dbFile.getName()).url(fileDownload).type(dbFile.getType())
					.size(dbFile.getData().length + " bytes").fecha(dbFile.getFecha().toString()).build();
		}).collect(Collectors.toList());
		return archivos;
	}

}
