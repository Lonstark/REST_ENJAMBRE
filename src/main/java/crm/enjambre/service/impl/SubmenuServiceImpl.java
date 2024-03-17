package crm.enjambre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.Submenu;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ISubmenuRepository;
import crm.enjambre.service.ISubmenuService;

@Service
public class SubmenuServiceImpl extends CRUDImpl<Submenu, Integer> implements ISubmenuService {

	@Autowired
	private ISubmenuRepository repo;

	@Override
	protected IGenericRepo<Submenu, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Optional<Submenu> findById(Integer idSubmenu) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Submenu> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}



}
