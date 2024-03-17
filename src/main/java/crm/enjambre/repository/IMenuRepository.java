package crm.enjambre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.Accion;
import crm.enjambre.model.Menu;

public interface IMenuRepository extends IGenericRepo<Menu, Integer>{
	
	/*
	@Query("SELECT DISTINCT psma.menu FROM PerfilSubMenuAccion psma " +
            "JOIN psma.menu " +
            "WHERE psma.perfil.idPerfil = :idPerfil " +
            "ORDER BY psma.menu.idMenu ASC")
    List<Menu> findMenusByPerfilId(@Param("idPerfil") Integer idPerfil);
	*/
	
	Optional<Menu> findById(Integer idMenu);
	Optional<Menu> findByNombre(String nombre);
}
