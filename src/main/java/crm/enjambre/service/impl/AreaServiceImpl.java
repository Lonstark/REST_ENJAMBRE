package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Area;
import crm.enjambre.repository.IAreaRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.IAreaService;

@Service
public class AreaServiceImpl extends CRUDImpl<Area, Integer> implements IAreaService {
	
	@Autowired
	private IAreaRepository repo;
	
	@Override
	protected IGenericRepo<Area, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public List<Area> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}

}
