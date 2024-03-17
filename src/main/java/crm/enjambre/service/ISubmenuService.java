package crm.enjambre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.Perfil;
import crm.enjambre.model.Submenu;

public interface ISubmenuService extends ICRUD<Submenu, Integer>{
	
	Optional<Submenu> findById(Integer idSubmenu);
	Optional<Submenu> findByNombre(String nombre);
	
}
