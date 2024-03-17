package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.TipoVenta;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ITipoVentaRepository;
import crm.enjambre.service.ITipoVentaService;

@Service
public class TipoVentaServiceImpl extends CRUDImpl<TipoVenta, Integer> implements ITipoVentaService{

	@Autowired
	private ITipoVentaRepository repo;
	
	@Override
	protected IGenericRepo<TipoVenta, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
