package crm.enjambre.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import crm.enjambre.model.Menu;

public interface IMenuService extends ICRUD<Menu, Integer> {
	Optional<Menu> findById(Integer idMenu);
	Optional<Menu> findByNombre(String nombre);
}
