package crm.enjambre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Usuario;

public interface IUsuarioService extends ICRUD<Usuario, Integer> {

	Optional<Usuario> findByIdUsuario(String idUsuario);
	@Query("SELECT u FROM Usuario u WHERE u.estado = 1")
    List<Usuario> findAllByEstado();
}
