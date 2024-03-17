package crm.enjambre.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.SubArea;

public interface ISubAreaService extends ICRUD<SubArea, Integer> {
	@Query("SELECT s FROM SubArea s WHERE s.area.idArea = :idArea")
	List<SubArea> findByAreaId(@Param("idArea") Integer idArea);
	
	@Query("SELECT s FROM SubArea s WHERE s.estado = 1")
    List<SubArea> findAllByEstado();
}
