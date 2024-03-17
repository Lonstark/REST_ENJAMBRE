package crm.enjambre.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario_certificado")
@IdClass(UsuarioCertificadoId.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioCertificado {

	@Id
	@ManyToOne
	@JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo")
	private Usuario usuario;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "certificado_id", referencedColumnName = "id")
	private Certificado certificado;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "f_inicio")
	@Temporal(TemporalType.DATE)
	private Date fechaInicio;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "f_fin")
	private Date fechaFin;
	
}


