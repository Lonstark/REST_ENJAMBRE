package crm.enjambre.auth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.servlet.http.HttpServletRequest;
import crm.enjambre.jwt.JwtService;
import crm.enjambre.model.TraceSesion;
import crm.enjambre.model.Usuario;
import crm.enjambre.model.UsuarioCertificado;
import crm.enjambre.repository.ITraceSesionRepository;
import crm.enjambre.repository.IUsuarioCertificadoRepository;
import crm.enjambre.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final HttpServletRequest requestIp;
	private final IUsuarioRepository usuarioRepository;
	private final JwtService jwtService;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final IUsuarioCertificadoRepository certificadoRepo;
	private final ITraceSesionRepository traceSesionRepository;

	private String getClientIp(HttpServletRequest request) {
		// Dirección del servicio para obtener la IP pública
		String ipServiceUrl = "https://api64.ipify.org?format=json";
		String ipAddress = null;

		try {
			URL url = new URL(ipServiceUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

			// Parsear la respuesta JSON para obtener la dirección IP pública
			String jsonResponse = content.toString();
			ipAddress = jsonResponse.substring(jsonResponse.indexOf(":\"") + 2, jsonResponse.indexOf("\"}"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return ipAddress;
	}

	public AuthResponse login(LoginRequest request) {
		// Autenticar al usuario
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getIdUsuario(), request.getContrasenia()));

		// Obtener el usuario autenticado
		UserDetails userDetails = usuarioRepository.findByIdUsuario(request.getIdUsuario())
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		// Obtener la IP del dispositivo
		String ip = getClientIp(requestIp);

		// Crear la sesión
		TraceSesion sesion = new TraceSesion();
		sesion.setUsuario((Usuario) userDetails); // Castear UserDetails a Usuario
		sesion.setFecha(new Date());
		sesion.setAccion("Inicio sesion");
		sesion.setEstado(1); // Estado 1: Sesión activa
		sesion.setIp(ip);

		// Guardar la sesión en la base de datos
		traceSesionRepository.save(sesion);

		// Obtener el token JWT
		String token = jwtService.getToken(userDetails);
		return AuthResponse.builder().token(token).build();
	}

	public AuthResponse register(RegisterRequest request) {
		// Generar idUsuario y contraseña basadas en el nombre y apellido del usuario
		// String idUsuario = generarIdUsuario(request.getNombres(),
		// request.getApellidos());
		// String contrasenia = generarContrasenia(request.getNombres(),
		// request.getApellidos());

		// Credenciales credenciales =
		// credencialRepository.findById("credenciales-id").orElse(null);

		// Crear el usuario con los datos proporcionados y los generados

		Usuario usuario = Usuario.builder().
				idUsuario(request.getIdUsuario())
				.nombres(request.getNombres())
				.apellidos(request.getApellidos())
				.ubigeo(request.getUbigeo())
				.direccion(request.getDireccion())
				.referencia(request.getReferencia())
				.tipoDocumento(request.getTipoDocumento())
				.documento(request.getDocumento())
				.fechaNacimiento(request.getFechaNacimiento())
				.email(request.getEmail())
				.celular(request.getCelular())
				.celularRef(request.getCelularRef())
				.sexo(request.getSexo())
				.ruc(request.getRuc())
				.estadoCivil(request.getEstadoCivil())
				.nombreConyuguente(request.getNombreConyuguente())
				.nhijos(request.getNhijos())
				.banco(request.getBanco())
				.tipoCuenta(request.getTipoCuenta())
				.moneda(request.getMoneda())
				.numeroCuenta(request.getNumeroCuenta())
				.ncci(request.getNcci())
				.estado(request.getEstado())
				.contrasenia(passwordEncoder.encode("123"))
				.perfil(request.getPerfil())
				.zona(request.getZona())
				.empresa(request.getEmpresa())
				.build();

		// Guardar el usuario en el repositorio
		usuarioRepository.save(usuario);

		for (UsuarioCertificado certificado : request.getCertificados()) {
			// Crear un nuevo objeto UsuarioCertificado y establecer el usuario y el
			// certificado
			UsuarioCertificado usuarioCertificado = new UsuarioCertificado();
			usuarioCertificado.setUsuario(usuario);
			usuarioCertificado.setCertificado(certificado.getCertificado());
			usuarioCertificado.setFechaInicio(certificado.getFechaInicio());
			usuarioCertificado.setFechaFin(certificado.getFechaFin());
			// Guardar el usuarioCertificado en el repositorio
			certificadoRepo.save(usuarioCertificado);
		}

		return AuthResponse.builder().token(jwtService.getToken(usuario)).build();
	}

	public AuthResponse actualizarContrasenia(String idUsuario, String contraseniaActual, String nuevaContrasenia) {
		// Buscar al usuario por su id
		Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

		// Verificar si la contraseña actual coincide con la contraseña almacenada en la
		// base de datos
		if (!passwordEncoder.matches(contraseniaActual, usuario.getContrasenia())) {
			throw new RuntimeException("La contraseña actual no coincide");
		}

		// Actualizar la contraseña
		usuario.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
		usuarioRepository.save(usuario);

		// Obtener el token JWT actualizado
		String token = jwtService.getToken(usuario);
		return AuthResponse.builder().token(token).build();
	}
	
	public AuthResponse actualizarContraseniaDesdeAdmin(String idUsuario, String nuevaContrasenia) {
	    // Buscar al usuario por su id
	    Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario)
	            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

	    // Actualizar la contraseña
	    usuario.setContrasenia(passwordEncoder.encode(nuevaContrasenia));
	    usuarioRepository.save(usuario);

	    // Obtener el token JWT actualizado
	    String token = jwtService.getToken(usuario);
	    return AuthResponse.builder().token(token).build();
	}
	
	
	/*
	 * private String generarIdUsuario(String nombres, String apellidos) { //
	 * Obtener la primera letra del primer nombre String primerNombre =
	 * nombres.trim().split("\\s+")[0]; String primeraLetraNombre =
	 * primerNombre.substring(0, 1).toLowerCase();
	 * 
	 * // Obtener solo el primer apellido String[] apellidosArray =
	 * apellidos.trim().split("\\s+"); String primerApellido =
	 * apellidosArray[0].toLowerCase();
	 * 
	 * // Concatenar la primera letra del primer nombre con el primer apellido
	 * String idUsuario = primeraLetraNombre + primerApellido;
	 * 
	 * // Verificar si el código de usuario ya existe en la base de datos
	 * Optional<Usuario> usuarioExistente =
	 * usuarioRepository.findByIdUsuario(idUsuario); if
	 * (usuarioExistente.isPresent()) { // Si el código de usuario existe, intentar
	 * con el segundo nombre y segundo // apellido if
	 * (nombres.trim().split("\\s+").length > 1) { String segundoNombre =
	 * nombres.trim().split("\\s+")[1]; String primeraLetraSegundoNombre =
	 * segundoNombre.substring(0, 1).toLowerCase(); // Concatenar la primera letra
	 * del segundo nombre con el segundo apellido if (apellidosArray.length > 1) {
	 * String segundoApellido = apellidosArray[1].toLowerCase(); idUsuario =
	 * primeraLetraSegundoNombre + segundoApellido; } else { // Si solo hay un
	 * apellido, mantener el primer apellido idUsuario = primeraLetraSegundoNombre +
	 * primerApellido; } } }
	 * 
	 * return idUsuario; }
	 */

	/*
	 * private String generarContrasenia(String nombres, String apellidos) { //
	 * Obtener la primera letra del nombre y convertirla a mayúscula String
	 * primerNombre = nombres.trim().substring(0, 1).toUpperCase();
	 * 
	 * // Obtener el primer apellido y convertir las vocales según lo especificado
	 * String[] apellidosArray = apellidos.trim().split("\\s+"); String
	 * primerApellido = ""; if (apellidosArray.length > 0) { primerApellido =
	 * apellidosArray[0]; primerApellido = reemplazarVocales(primerApellido); }
	 * 
	 * // Concatenar el primer nombre y el primer apellido String contrasenia =
	 * primerNombre + primerApellido;
	 * 
	 * // Concatenar ".123" al final para la contraseña contrasenia += ".123";
	 * return contrasenia; }
	 */

	/*
	 * private String reemplazarVocales(String apellido) { apellido =
	 * apellido.toLowerCase(); apellido = apellido.replaceAll("a", "4"); apellido =
	 * apellido.replaceAll("e", "3"); apellido = apellido.replaceAll("i", "1");
	 * apellido = apellido.replaceAll("o", "0"); return apellido; }
	 */

}