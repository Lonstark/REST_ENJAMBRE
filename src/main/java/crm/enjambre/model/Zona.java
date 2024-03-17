package crm.enjambre.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "zona")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Zona {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idZona;

	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "estado")
	private Integer estado;
}
