package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Accion;
import crm.enjambre.model.Menu;
import crm.enjambre.model.PerfilAccion;
import crm.enjambre.model.Submenu;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IPerfilAccionRepository;
import crm.enjambre.service.IPerfilAccionService;

@Service
public class PerfilAccionServiceImpl extends CRUDImpl<PerfilAccion, Integer> implements IPerfilAccionService {

	@Autowired
	private IPerfilAccionRepository repo;

	@Override
	protected IGenericRepo<PerfilAccion, Integer> getRepo() {
		return repo;
	}

	@Override
	public List<Menu> findDistinctMenuByPerfilId(Integer idPerfil) {
		// TODO Auto-generated method stub
		return repo.findDistinctMenuByPerfilId(idPerfil);
	}

	@Override
	public List<Submenu> findDistinctSubMenuByPerfilIdAndMenuId(Integer idPerfil, Integer idMenu) {
		// TODO Auto-generated method stub
		return repo.findDistinctSubMenuByPerfilIdAndMenuId(idPerfil, idMenu);
	}

	@Override
	public List<Accion> findDistinctAccionesByPerfilIdAndSubMenuId(Integer idPerfil, Integer idSubmenu) {
		// TODO Auto-generated method stub
		return repo.findDistinctAccionesByPerfilIdAndSubMenuId(idPerfil, idSubmenu);
	}
	
	

}
