package crm.enjambre.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class UsuarioPerfilId implements Serializable {
	private String usuario;
	private Integer perfil;

	public UsuarioPerfilId() {
		super();
	}

	public UsuarioPerfilId(String usuario, Integer perfil) {
		super();
		this.usuario = usuario;
		this.perfil = perfil;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioPerfilId other = (UsuarioPerfilId) obj;
		return Objects.equals(perfil, other.perfil) && Objects.equals(usuario, other.usuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(perfil, usuario);
	}

}
