package crm.enjambre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.Menu;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IMenuRepository;
import crm.enjambre.service.IMenuService;

@Service
public class MenuServiceImpl extends CRUDImpl<Menu, Integer> implements IMenuService{

	@Autowired
	private IMenuRepository repo;
	
	@Override
	protected IGenericRepo<Menu, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Optional<Menu> findById(Integer idMenu) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Menu> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



}
