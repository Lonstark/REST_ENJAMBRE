package crm.enjambre.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crm.enjambre.model.TipoDocumento;
import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.repository.ITipoDocumentoRepository;
import crm.enjambre.service.ITipoDocumentoService;

@Service
public class TipoDocumentoServiceImpl extends CRUDImpl<TipoDocumento, Integer> implements ITipoDocumentoService {

	@Autowired
	private ITipoDocumentoRepository repo;

	@Override
	protected IGenericRepo<TipoDocumento, Integer> getRepo() {
		// TODO Auto-generated method stub
		return repo;
	}

}
