package crm.enjambre.model;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "area")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Area {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArea;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "descripcion")
	private String descripcion;

	@Column(name = "estado")
    private Integer estado;
	
	@ManyToMany
	@JoinTable(name = "area_empresa", joinColumns = @JoinColumn(name = "area_id"), inverseJoinColumns = @JoinColumn(name = "empresa_id"))
	private Set<Empresa> empresas;
}

