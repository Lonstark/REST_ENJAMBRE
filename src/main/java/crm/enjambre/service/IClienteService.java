package crm.enjambre.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Cliente;

public interface IClienteService extends ICRUD<Cliente, Integer> {
	@Query("SELECT c FROM Cliente c WHERE c.estado = 1")
    List<Cliente> findAllByEstado();
}
