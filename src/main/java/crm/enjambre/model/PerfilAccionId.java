package crm.enjambre.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class PerfilAccionId implements Serializable {
	
	private Integer perfil;
	private Integer accion;
	
	public PerfilAccionId() {
		super();
	}

	public PerfilAccionId(Integer perfil, Integer accion) {
		super();
		this.perfil = perfil;
		this.accion = accion;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerfilAccionId other = (PerfilAccionId) obj;
		return Objects.equals(perfil, other.perfil) && Objects.equals(accion, other.accion);
	}

	@Override
	public int hashCode() {
		return Objects.hash(perfil, accion);
	}
	
}
