package crm.enjambre.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.Accion;
import crm.enjambre.model.Menu;
import crm.enjambre.model.PerfilAccion;
import crm.enjambre.model.Submenu;

public interface IPerfilAccionService extends ICRUD<PerfilAccion, Integer> {

	 @Query("SELECT DISTINCT m FROM PerfilAccion pf " +
	           "INNER JOIN pf.accion a " +
	           "INNER JOIN a.submenues s " +
	           "INNER JOIN s.menu m " +
	           "WHERE pf.perfil.idPerfil = :idPerfil " +
	           "ORDER BY m.idMenu ASC")
	 List<Menu> findDistinctMenuByPerfilId(@Param("idPerfil") Integer idPerfil);
	
	 
	 @Query("SELECT DISTINCT s FROM PerfilAccion pf " +
	           "INNER JOIN pf.accion a " +
	           "INNER JOIN a.submenues s " +
	           "INNER JOIN s.menu m " +
	           "WHERE pf.perfil.idPerfil = :idPerfil " +
	           "AND m.idMenu = :idMenu " +
	           "ORDER BY s.idSubmenu ASC")
	 List<Submenu> findDistinctSubMenuByPerfilIdAndMenuId(@Param("idPerfil") Integer idPerfil, @Param("idMenu") Integer idMenu);
	
	 @Query("SELECT DISTINCT a FROM PerfilAccion pf " +
	           "INNER JOIN pf.accion a " +
	           "INNER JOIN a.submenues s " +
	           "INNER JOIN s.menu m " +
	           "WHERE pf.perfil.idPerfil = :idPerfil " +
	           "AND s.idSubmenu = :idSubmenu " +
	           "ORDER BY a.idAccion ASC")
	 List<Accion> findDistinctAccionesByPerfilIdAndSubMenuId(@Param("idPerfil") Integer idPerfil, @Param("idSubmenu") Integer idSubmenu);
	
}
