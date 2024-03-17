package crm.enjambre.auth;

import java.util.Date;
import java.util.List;
import java.util.Set;

import crm.enjambre.model.Empresa;
import crm.enjambre.model.Perfil;
import crm.enjambre.model.TipoDocumento;
import crm.enjambre.model.Ubigeo;
import crm.enjambre.model.UsuarioCertificado;
import crm.enjambre.model.Zona;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	private String idUsuario;
	private String nombres;
	private String apellidos;
	private Ubigeo ubigeo;
	private String direccion;
	private String referencia;
	private TipoDocumento tipoDocumento;
	private String documento;
	private Date fechaNacimiento;
	private String email;
	private String celular;
	private String celularRef;
	private String sexo;
	private String ruc;
	private String estadoCivil;
	private String nombreConyuguente;
	private Integer nhijos;
	private String banco;
	private String tipoCuenta;
	private String moneda;
	private String numeroCuenta;
	private String ncci;
	private Integer estado;
	private String contrasenia;
	private Set<Perfil> perfil;
	private Set<Zona> zona;
	private Set<Empresa> empresa;
	private List<UsuarioCertificado> certificados;
}
