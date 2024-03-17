package crm.enjambre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.Accion;

public interface IAccionRepository extends IGenericRepo<Accion, Integer> {
	
	Optional<Accion> findById(Integer idAccion);
	Optional<Accion> findByNombre(String nombre);
}
