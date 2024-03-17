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
@Table(name = "ubigeo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ubigeo {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUbigeo;

	@Column(name = "departamento")
	private String departamento;
	
	@Column(name = "provincia")
	private String provincia;
	
	@Column(name = "distrito")
	private String distrito;
	
	
}
