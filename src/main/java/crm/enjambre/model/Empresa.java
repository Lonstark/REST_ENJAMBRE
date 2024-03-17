
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
@Table(name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
	
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmpresa;
	
	@Column(name = "ruc")
	private String ruc;

	@Column(name = "correo")
	private String correo;
	
	@Column(name = "direccion")
	private String direccion;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "estado")
    private Integer estado;
	
	@ManyToMany
	@JoinTable(name = "empresa_cliente", joinColumns = @JoinColumn(name = "empresa_id"), inverseJoinColumns = @JoinColumn(name = "cliente_id"))
	private Set<Cliente> clientes;
}
