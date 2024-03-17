package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.CredencialesContratadas;
import crm.enjambre.repository.ICredencialesContratadasRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.ICredencialesContratadasService;

@Service
public class CredencialesContratadasServiceImpl extends CRUDImpl<CredencialesContratadas, Integer> implements ICredencialesContratadasService{

	@Autowired
	private ICredencialesContratadasRepository repo;
	
	@Override
	protected IGenericRepo<CredencialesContratadas, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
