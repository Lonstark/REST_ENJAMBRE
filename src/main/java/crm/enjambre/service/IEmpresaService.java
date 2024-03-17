package crm.enjambre.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Empresa;

public interface IEmpresaService extends ICRUD<Empresa, Integer> {
	@Query("SELECT e FROM Empresa e WHERE e.estado = 1")
    List<Empresa> findAllByEstado();
}
