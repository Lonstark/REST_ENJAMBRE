package crm.enjambre.service.impl;

import java.util.List;
import java.util.Optional;

import crm.enjambre.repository.IGenericRepo;
import crm.enjambre.service.ICRUD;
import jakarta.persistence.EntityNotFoundException;

public abstract class CRUDImpl<T, ID> implements ICRUD<T, ID> {

	protected abstract IGenericRepo<T, ID> getRepo();

	@Override
	public T registrar(T t) {
		return getRepo().save(t);
	}

	@Override
	public T modificar(T t) {
		Optional<T> entityOptional = Optional.ofNullable(getRepo().save(t));
		if (entityOptional.isPresent()) {
			return entityOptional.get();
		} else {
			throw new EntityNotFoundException("La entidad no pudo ser modificada");
		}
	}

	@Override
	public List<T> listar() {
		Optional<List<T>> entitiesOptional = Optional.ofNullable(getRepo().findAll());
		return entitiesOptional.orElseThrow(() -> new EntityNotFoundException("No se encontraron entidades"));
	}

	@Override
	public T listarPorId(ID id) {
		Optional<T> optionalEntity = getRepo().findById(id);
		return optionalEntity.orElse(null);
	}

	@Override
	public void eliminar(ID id) {
		Optional<T> entityOptional = getRepo().findById(id);
		if (entityOptional.isPresent()) {
			getRepo().deleteById(id);
		} else {
			throw new EntityNotFoundException("La entidad con ID " + id + " no fue encontrada");
		}
	}

}
