package crm.enjambre.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trace_sesion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceSesion {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSesion;

	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "codigo")
	private Usuario usuario;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Column(name = "accion")
	private String accion;

	@Column(name = "estado")
	private Integer estado;
	
	@Column(name = "ip")
	private String ip;
}
