package crm.enjambre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Perfil;

public interface IPerfilRepository extends IGenericRepo<Perfil, Integer> {

	Optional<Perfil> findByIdPerfil(Integer idPerfil);
	Optional<Perfil> findByNombre(String nombre);
	@Query("SELECT p FROM Perfil p WHERE p.estado = 1")
    List<Perfil> findAllByEstado();
}
