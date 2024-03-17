package crm.enjambre.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_perfil")
@IdClass(UsuarioPerfilId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPerfil {
	@Id
	@ManyToOne
	@JoinColumn(name = "usuario_id", referencedColumnName = "codigo")
	private Usuario usuario;

	@Id
	@ManyToOne
	@JoinColumn(name = "perfil_id", referencedColumnName = "id")
	private Perfil perfil;
}
