package crm.enjambre.model;

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Table(name = "usuario", uniqueConstraints = { @UniqueConstraint(columnNames = { "email", "codigo" }) })
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

	@Id
	@Column(name = "codigo")
	private String idUsuario;

	@Column(name = "nombres")
	private String nombres;

	@Column(name = "apellidos")
	private String apellidos;

	@ManyToOne
	@JoinColumn(name = "ubigeo_id")
	private Ubigeo ubigeo;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "referencia")
	private String referencia;

	@ManyToOne
	@JoinColumn(name = "tipo_documento_id")
	private TipoDocumento tipoDocumento;

	@Column(name = "documento")
	private String documento;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@Column(name = "f_nacimiento")
	@Temporal(TemporalType.DATE)
	private Date fechaNacimiento;

	@Column(name = "email")
	private String email;

	@Column(name = "celular")
	private String celular;

	@Column(name = "celular_ref")
	private String celularRef;

	@Column(name = "sexo")
	private String sexo;

	@Column(name = "ruc")
	private String ruc;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Column(name = "nombre_conyuguente")
	private String nombreConyuguente;

	@Column(name = "n_hijos")
	private Integer nhijos;

	@Column(name = "banco")
	private String banco;

	@Column(name = "tipo_cuenta")
	private String tipoCuenta;

	@Column(name = "moneda")
	private String moneda;

	@Column(name = "numero_cuenta")
	private String numeroCuenta;

	@Column(name = "n_cci")
	private String ncci;

	@Column(name = "estado")
	private Integer estado;

	@Column(name = "contrasenia")
	private String contrasenia;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_perfil", joinColumns = @JoinColumn(name = "usuario_codigo"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
	private Set<Perfil> perfil;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_zona", joinColumns = @JoinColumn(name = "usuario_codigo"), inverseJoinColumns = @JoinColumn(name = "zona_id"))
	private Set<Zona> zona;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_empresa", joinColumns = @JoinColumn(name = "usuario_codigo"), inverseJoinColumns = @JoinColumn(name = "empresa_id"))
	private Set<Empresa> empresa;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfil.stream().map(perfil -> new SimpleGrantedAuthority(perfil.getNombre()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return contrasenia;
	}

	@Override
	public String getUsername() {
		return idUsuario;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}