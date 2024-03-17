package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.TipoInmueble;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ITipoInmuebleRepository;
import crm.enjambre.service.ITipoInmuebleService;

@Service
public class TipoInmuebleServiceImpl extends CRUDImpl<TipoInmueble, Integer> implements ITipoInmuebleService{

	@Autowired
	private ITipoInmuebleRepository repo;
	
	@Override
	protected IGenericRepo<TipoInmueble, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
