package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.SubArea;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ISubAreaRepository;
import crm.enjambre.service.ISubAreaService;

@Service
public class SubAreaServiceImpl extends CRUDImpl<SubArea, Integer> implements ISubAreaService {

	@Autowired
	private ISubAreaRepository repo;

	@Override
	protected IGenericRepo<SubArea, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public List<SubArea> findByAreaId(Integer idArea) {
		// TODO Auto-generated method stub
		return repo.findByAreaId(idArea);
	}

	@Override
	public List<SubArea> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}

}
