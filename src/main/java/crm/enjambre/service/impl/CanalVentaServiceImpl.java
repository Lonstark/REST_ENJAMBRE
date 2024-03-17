package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.CanalVenta;
import crm.enjambre.repository.ICanalVentaRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.ICanalVentaService;

@Service
public class CanalVentaServiceImpl extends CRUDImpl<CanalVenta, Integer> implements ICanalVentaService{

	@Autowired
	private ICanalVentaRepository repo;
	
	@Override
	protected IGenericRepo<CanalVenta, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
