package crm.enjambre.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Area;

public interface IAreaService extends ICRUD<Area, Integer> {
	@Query("SELECT a FROM Area a WHERE a.estado = 1")
    List<Area> findAllByEstado();
}
