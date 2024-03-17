package crm.enjambre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Cliente;

public interface IClienteRepository extends IGenericRepo<Cliente, Integer> {
	@Query("SELECT c FROM Cliente c WHERE c.estado = 1")
    List<Cliente> findAllByEstado();
}
