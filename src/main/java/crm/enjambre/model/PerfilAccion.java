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
@Table(name = "perfil_accion")
@IdClass(PerfilAccionId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilAccion {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "perfil_id", referencedColumnName = "id")
	private Perfil perfil;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "accion_id", referencedColumnName = "id")
	private Accion accion;
	
}
