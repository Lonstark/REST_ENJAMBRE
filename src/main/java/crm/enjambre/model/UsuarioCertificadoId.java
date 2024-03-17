package crm.enjambre.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class UsuarioCertificadoId implements Serializable {
	
	private String usuario;
	private Integer certificado;

	public UsuarioCertificadoId() {
	}

	public UsuarioCertificadoId(String usuario, Integer certificado) {
		super();
		this.usuario = usuario;
		this.certificado = certificado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UsuarioCertificadoId that = (UsuarioCertificadoId) o;
		return Objects.equals(usuario, that.usuario) && Objects.equals(certificado, that.certificado);
	}

	@Override
	public int hashCode() {
		return Objects.hash(usuario, certificado);
	}
	
}
