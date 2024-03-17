package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import crm.enjambre.model.UsuarioCertificado;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.IUsuarioCertificadoRepository;
import crm.enjambre.service.IUsuarioCertificadoService;

@Service
public class UsuarioCertificadoServiceImpl extends CRUDImpl<UsuarioCertificado, Integer> implements IUsuarioCertificadoService{

	@Autowired
	private IUsuarioCertificadoRepository repo;
	
	@Override
	protected IGenericRepo<UsuarioCertificado, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
