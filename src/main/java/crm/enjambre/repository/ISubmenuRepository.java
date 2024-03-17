package crm.enjambre.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import crm.enjambre.model.Submenu;

public interface ISubmenuRepository extends IGenericRepo<Submenu, Integer> {
	// List<Submenu> findByMenuId(Integer menuId);

	//@Query("SELECT s FROM Submenu s WHERE s.menu.idMenu = :idMenu ORDER BY s.nombre ASC")
    //List<Submenu> findByMenuId(@Param("idMenu") Integer idMenu);
	
	Optional<Submenu> findById(Integer idSubmenu);
	Optional<Submenu> findByNombre(String nombre);
	
}
