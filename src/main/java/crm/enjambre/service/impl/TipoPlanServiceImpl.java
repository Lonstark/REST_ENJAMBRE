package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.TipoPlan;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ITipoPlanRepository;
import crm.enjambre.service.ITipoPlanService;

@Service
public class TipoPlanServiceImpl extends CRUDImpl<TipoPlan, Integer> implements ITipoPlanService{

	@Autowired
	private ITipoPlanRepository repo;
	
	@Override
	protected IGenericRepo<TipoPlan, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
