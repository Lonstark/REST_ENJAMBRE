package crm.enjambre.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import crm.enjambre.model.Archivo;
import crm.enjambre.model.Venta;
import crm.enjambre.response.ResponseArchivo;

public interface IArchivoService extends ICRUD<Archivo, UUID> {

	// Permite almacenar o cargar archivos a BD
	//Archivo guardarUnAr(MultipartFile file) throws IOException;

	// Permite almacenar o cargar archivos a BD
	// List<Archivo> guardarArchivos(List<MultipartFile> files) throws IOException;

	// Convierte una lista de archivos MultipartFile a una lista de ArchivoModel
	List<Archivo> convertirList(List<MultipartFile> files, Venta v) throws IOException;

	// Permite descargar archivos por UUID de la BD
	Optional<Archivo> getFileXdownload(UUID id) throws FileNotFoundException;

	// Permite consultar la lista de archivos que hay en la BD
	List<ResponseArchivo> getAllFiles();

}
