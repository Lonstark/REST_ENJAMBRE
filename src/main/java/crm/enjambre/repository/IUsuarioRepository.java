package crm.enjambre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Usuario;

public interface IUsuarioRepository extends IGenericRepo<Usuario, Integer> {

	Optional<Usuario> findByIdUsuario(String idUsuario);
	Optional<Usuario> findByEmail(String email);
	@Query("SELECT u FROM Usuario u WHERE u.estado = 1")
    List<Usuario> findAllByEstado();
}
