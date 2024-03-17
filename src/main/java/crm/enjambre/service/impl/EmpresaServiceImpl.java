package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Empresa;
import crm.enjambre.repository.IEmpresaRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.IEmpresaService;

@Service
public class EmpresaServiceImpl extends CRUDImpl<Empresa, Integer> implements IEmpresaService {

	@Autowired
	private IEmpresaRepository repo;

	@Override
	protected IGenericRepo<Empresa, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public List<Empresa> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}
	
	
}
