package crm.enjambre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Certificado;

public interface ICertificadoRepository extends IGenericRepo<Certificado, Integer> {
	@Query("SELECT MAX(CAST(SUBSTRING(e.codigo, 4) AS INTEGER)) FROM Certificado e WHERE SUBSTRING(e.codigo, 1, 3) = 'CT-'")
	Integer obtenerCertificado();
	
	@Query("SELECT c FROM Certificado c WHERE c.estado = 1")
    List<Certificado> findAllByEstado();
}
