package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.Cargo;
import crm.enjambre.repository.ICargoRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.ICargoService;

@Service
public class CargoServiceImpl extends CRUDImpl<Cargo, Integer> implements ICargoService{

	@Autowired
	private ICargoRepository repo;
	
	@Override
	protected IGenericRepo<Cargo, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
