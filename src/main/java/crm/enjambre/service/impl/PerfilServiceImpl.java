package crm.enjambre.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.Perfil;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IPerfilRepository;
import crm.enjambre.service.IPerfilService;

@Service
public class PerfilServiceImpl extends CRUDImpl<Perfil, Integer> implements IPerfilService {

	@Autowired
	private IPerfilRepository repo;

	@Override
	protected IGenericRepo<Perfil, Integer> getRepo() {
		return repo;
	}

	@Override
	public Optional<Perfil> findByIdPerfil(Integer idPerfil) {
		// TODO Auto-generated method stub
		return repo.findById(idPerfil);
	}

	@Override
	public Optional<Perfil> findByNombre(String nombre) {
		// TODO Auto-generated method stub
		return repo.findByNombre(nombre);
	}

	@Override
	public List<Perfil> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}

	

	

}
