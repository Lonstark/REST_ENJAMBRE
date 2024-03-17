package crm.enjambre.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import crm.enjambre.model.Empresa;

public interface IEmpresaRepository extends IGenericRepo<Empresa, Integer> {
	@Query("SELECT e FROM Empresa e WHERE e.estado = 1")
    List<Empresa> findAllByEstado();
}
