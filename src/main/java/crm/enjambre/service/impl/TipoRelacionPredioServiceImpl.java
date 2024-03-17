package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.TipoRelacionPredio;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ITipoRelacionPredioRepository;
import crm.enjambre.service.ITipoRelacionPredioService;

@Service
public class TipoRelacionPredioServiceImpl extends CRUDImpl<TipoRelacionPredio, Integer> implements ITipoRelacionPredioService{

	@Autowired
	private ITipoRelacionPredioRepository repo;
	
	@Override
	protected IGenericRepo<TipoRelacionPredio, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
