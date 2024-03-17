package crm.enjambre.model;

import java.util.Date;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "archivo")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Archivo {

	@Id
	@GeneratedValue
	@Column(name = "id_archivo")
	private UUID idArchivo;

	@Column(name = "desc_archivo")
	private Integer descripcionArchivo;

	@Column(name = "nombre")
	private String name;

	@Column(name = "tipo")
	private String type;
	
	@Column(name = "url")
	private String url;
	
	@Lob
	@Column(name = "data_arc", columnDefinition = "LONGBLOB")
	private byte[] data;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "fecha", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@ManyToOne
	@JoinColumn(name = "venta_id")
	@JsonIgnore
	private Venta venta;
}
