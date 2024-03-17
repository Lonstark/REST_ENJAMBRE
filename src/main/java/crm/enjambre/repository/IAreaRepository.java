package crm.enjambre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Area;

public interface IAreaRepository extends IGenericRepo<Area, Integer> {
	@Query("SELECT a FROM Area a WHERE a.estado = 1")
    List<Area> findAllByEstado();
}
