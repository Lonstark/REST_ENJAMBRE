package crm.enjambre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.Accion;
import crm.enjambre.model.Perfil;
import crm.enjambre.repository.IAccionRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.IAccionService;

@Service
public class AccionServiceImpl extends CRUDImpl<Accion, Integer> implements IAccionService{

	@Autowired
	private IAccionRepository repo;
	
	@Override
	protected IGenericRepo<Accion, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Optional<Accion> findById(Integer idAccion) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<Accion> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}


}
