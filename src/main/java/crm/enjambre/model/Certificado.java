package crm.enjambre.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "certificado")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Certificado {
	@Id
	@Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCertificado;
	
	@Column(name = "codigo")
    private String codigo;
	
	@ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
	
	@ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
	
	@Column(name = "descripcion")
    private String descripcion;
	
	@Column(name = "estado")
	private Integer estado;
}
