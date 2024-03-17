package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.CredencialesDisponibles;
import crm.enjambre.repository.ICredencialesDisponiblesRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.ICredencialesDisponiblesService;

@Service
public class CredencialesDisponiblesServiceImpl extends CRUDImpl<CredencialesDisponibles, Integer> implements ICredencialesDisponiblesService{

	@Autowired
	private ICredencialesDisponiblesRepository repo;
	
	@Override
	protected IGenericRepo<CredencialesDisponibles, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
