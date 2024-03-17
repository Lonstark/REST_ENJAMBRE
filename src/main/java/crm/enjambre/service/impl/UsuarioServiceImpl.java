package crm.enjambre.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Usuario;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IUsuarioRepository;
import crm.enjambre.service.IUsuarioService;

@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements IUsuarioService {

	@Autowired
	private IUsuarioRepository repo;

	@Override
	protected IGenericRepo<Usuario, Integer> getRepo() {
		return repo;
	}

	@Override
	public Optional<Usuario> findByIdUsuario(String idUsuario) {
		return repo.findByIdUsuario(idUsuario);
	}

	@Override
	public List<Usuario> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}


}
