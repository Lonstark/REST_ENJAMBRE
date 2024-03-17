package crm.enjambre.service;

import java.util.Optional;

import crm.enjambre.model.Accion;

public interface IAccionService extends ICRUD<Accion, Integer>{
	
	Optional<Accion> findById(Integer idAccion);
	Optional<Accion> findByNombre(String nombre);

}
