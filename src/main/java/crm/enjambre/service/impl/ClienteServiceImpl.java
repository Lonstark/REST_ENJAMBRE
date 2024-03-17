package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Cliente;
import crm.enjambre.repository.IClienteRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.IClienteService;

@Service
public class ClienteServiceImpl extends CRUDImpl<Cliente, Integer> implements IClienteService {

	@Autowired
	private IClienteRepository repo;

	@Override
	protected IGenericRepo<Cliente, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public List<Cliente> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}
}
