package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Zona;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IZonaRepository;
import crm.enjambre.service.IZonaService;

@Service
public class ZonaServiceImpl extends CRUDImpl<Zona, Integer> implements IZonaService{

	@Autowired
	private IZonaRepository repo;
	
	@Override
	protected IGenericRepo<Zona, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
