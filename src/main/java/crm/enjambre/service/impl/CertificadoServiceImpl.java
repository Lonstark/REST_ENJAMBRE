package crm.enjambre.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.Certificado;
import crm.enjambre.repository.ICertificadoRepository;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.ICertificadoService;

@Service
public class CertificadoServiceImpl extends CRUDImpl<Certificado, Integer> implements ICertificadoService{

	@Autowired
	private ICertificadoRepository repo;
	
	@Override
	protected IGenericRepo<Certificado, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

	@Override
	public Integer obtenerCertificado() {
		// TODO Auto-generated method stub
		return repo.obtenerCertificado();
	}

	@Override
	public List<Certificado> findAllByEstado() {
		// TODO Auto-generated method stub
		return repo.findAllByEstado();
	}

}
