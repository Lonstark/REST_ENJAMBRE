package crm.enjambre.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.Ubigeo;

public interface IUbigeoService extends ICRUD<Ubigeo, Integer>{

	@Query("SELECT DISTINCT u.departamento FROM Ubigeo u")
	 List<String> getDepartamentos();
	
	@Query("SELECT DISTINCT u.provincia FROM Ubigeo u WHERE u.departamento = :departamento")
	List<String> getProvincias(@Param("departamento") String departamento);
	 
	@Query("SELECT DISTINCT u FROM Ubigeo u WHERE u.provincia = :provincia")
	List<Ubigeo> getDistritos(@Param("provincia") String provincia);

	
}
