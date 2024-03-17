package crm.enjambre.auth;

import java.security.Principal;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import crm.enjambre.model.*;
import crm.enjambre.repository.*;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

	private final AuthService authService;
	private final IUsuarioRepository usuarioRepository;
	private final ICredencialesDisponiblesRepository credencialesDisponiblesRepository;
	private final ICredencialesContratadasRepository credencialesContratadasRepository;

	@PostMapping(value = "login")
	public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
		AuthResponse response = authService.login(request);

		// Verificar si el estado del usuario es activo
		Usuario usuario = usuarioRepository.findByIdUsuario(request.getIdUsuario())
				.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
		if (usuario.getEstado() != 1) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null); // Usuario no autorizado
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping(value = "register")
	public ResponseEntity<?> registerAdmin(@RequestBody RegisterRequest request) {
		try {

			/*
			 * Optional<Usuario> usuarioByIdUsuario =
			 * usuarioRepository.findByIdUsuario(request.getIdUsuario()); if
			 * (usuarioByIdUsuario.isPresent()) { return
			 * ResponseEntity.badRequest().body("El código de usuario ya está registrado");
			 * }
			 */

			// Verificar si el correo electrónico ya está registrado
			Optional<Usuario> usuarioByEmail = usuarioRepository.findByEmail(request.getEmail());
			if (usuarioByEmail.isPresent()) {
				return ResponseEntity.badRequest().body("El correo electrónico ya está registrado");
			}

			// Obtener las credenciales contratadas y disponibles
			CredencialesContratadas credencialesContratadas = credencialesContratadasRepository.findById(1)
					.orElse(null);
			CredencialesDisponibles credencialesDisponibles = credencialesDisponiblesRepository.findById(1)
					.orElse(null);

			// Verificar si las credenciales contratadas y disponibles existen
			if (credencialesContratadas == null || credencialesDisponibles == null) {
				return ResponseEntity.badRequest()
						.body("No se encontraron las credenciales contratadas o disponibles ");
			}

			// Verificar si hay credenciales disponibles
			if (credencialesDisponibles.getCantidad() <= 0) {
				return ResponseEntity.badRequest().body("No hay credenciales disponibles. Credenciales Disponibles: "
						+ credencialesDisponibles.getCantidad());
			}

			// Verificar si la cantidad de usuarios existentes supera la cantidad permitida
			long cantidadUsuarios = usuarioRepository.count();
			if (cantidadUsuarios >= credencialesContratadas.getCantidad()) {
				return ResponseEntity.badRequest().body("Se superó la cantidad máxima de usuarios permitidos");
			}

			// Intentar registrar el usuario
			AuthResponse response = authService.register(request);

			// Disminuir la cantidad de credenciales disponibles después de registrar un
			// usuario
			credencialesDisponibles.setCantidad(credencialesDisponibles.getCantidad() - 1);
			credencialesDisponiblesRepository.save(credencialesDisponibles);

			return ResponseEntity.ok(response);
		} catch (RuntimeException ex) {
			// Capturar excepciones y devolver mensaje de error
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@GetMapping(value = "actual-usuario")
	public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
		if (principal == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no autenticado");
		}
		Optional<Usuario> user = this.usuarioRepository.findByIdUsuario(principal.getName());
		return ResponseEntity.ok(user);
	}

	@PostMapping(value = "actualizar-contrasenia")
	public ResponseEntity<?> actualizarContrasenia(@RequestBody ActualizarContraseniaRequest request) {
		try {
			AuthResponse response = authService.actualizarContrasenia(request.getIdUsuario(),
					request.getContraseniaActual(), request.getNuevaContrasenia());
			return ResponseEntity.ok(response);
		} catch (RuntimeException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PostMapping(value = "actualizar-contrasenia-profile")
	public ResponseEntity<?> actualizarContraseniaAdmin(@RequestBody ActualizarContraseniaPerfilRequest request) {
		try {
			AuthResponse response = authService.actualizarContraseniaDesdeAdmin(request.getIdUsuario(),
					request.getNuevaContrasenia());
			return ResponseEntity.ok(response);
		} catch (RuntimeException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

}
