package crm.enjambre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Perfil;

public interface IPerfilService extends ICRUD<Perfil, Integer> {
	
	Optional<Perfil> findByIdPerfil(Integer idPerfil);
	Optional<Perfil> findByNombre(String nombre);
	@Query("SELECT p FROM Perfil p WHERE p.estado = 1")
    List<Perfil> findAllByEstado();
}
