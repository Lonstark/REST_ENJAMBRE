package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.TraceSesion;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ITraceSesionRepository;
import crm.enjambre.service.ITraceSesionService;

@Service
public class TraceSesionServiceImpl extends CRUDImpl<TraceSesion, Integer> implements ITraceSesionService{

	@Autowired
	private ITraceSesionRepository repo;
	
	@Override
	protected IGenericRepo<TraceSesion, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
