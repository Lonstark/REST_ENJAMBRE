package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.Ubigeo;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IUbigeoRepository;
import crm.enjambre.service.IUbigeoService;

@Service
public class UbigeoServiceImpl extends CRUDImpl<Ubigeo, Integer> implements IUbigeoService{

	@Autowired
	private IUbigeoRepository repo;
	
	@Override
	protected IGenericRepo<Ubigeo, Integer> getRepo() {
		return repo;
	}

	@Override
	public List<String> getDepartamentos() {
		return repo.getDepartamentos();
	}

	@Override
	public List<String> getProvincias(String departamento) {
		return repo.getProvincias(departamento);
	}

	@Override
	public List<Ubigeo> getDistritos(String provincia) {
		return repo.getDistritos(provincia);
	}

}
