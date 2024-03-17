package crm.enjambre.model;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "venta")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_venta")
	private Integer idVenta;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "certific_id")
	private Certificado certificado;

	@ManyToOne
	@JoinColumn(name = "zona_id")
	private Zona zona;

	@Column(name = "agendada")
	private boolean agendada;

	@Column(name = "tipo_venta")
	private String tipoVenta;

	// DATOS DEL CLIENTE
	@Column(name = "nombre")
	private String nombre;

	@Column(name = "ape_pat")
	private String apePat;

	@Column(name = "ape_mat")
	private String apeMat;

	@ManyToOne
	@JoinColumn(name = "ubigeo_id")
	private Ubigeo ubigeo;

	@Column(name = "direccion")	
	private String direccion;

	@Column(name = "coord")
	private String coordenadas;

	@Column(name = "referencia")
	private String referencia;

	@ManyToOne
	@JoinColumn(name = "tipo_doc_id")
	private TipoDocumento tipoDoc;

	@Column(name = "documento")
	private String documento;

	@Column(name = "nro_uno")
	private String nroUno;
	
	@Column(name = "nro_dos")
	private String nroDos;
	
	@Column(name = "nro_tres")
	private String nroTres;

	@Column(name = "whatsapp")
	private String whatsapp;
	
	@Column(name = "email")
	private String email;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fec_programada")
	@Temporal(TemporalType.DATE)
	private Date fecProgramada;
	
	@Column(name = "h_programada", length = 5)
	//@Temporal(TemporalType.TIME)
	private LocalTime horaProgramada;

	// DATOS DE LA VENTA
	@ManyToOne
	@JoinColumn(name = "canal_venta_id")
	private CanalVenta canalVenta;

	@Column(name = "tipo_inmueble")
	private String tipoInmueble;

	@ManyToOne
	@JoinColumn(name = "plan_id")
	private Plan planContratado;

	@Column(name = "cuotas_instal")
	private Integer cuotasInstal;

	@Column(name = "cant_mesh")
	private Integer cantMesh;

	@Column(name = "winbox")
	private String winbox;
	
	@Column(name = "nro_portar")
	private String nroPortar;

	@Column(name = "relacion_precio")
	private String relacionPrecio;
	
	@Column(name = "obs")
	private String obs;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Archivo> venta = new ArrayList<>();
}
