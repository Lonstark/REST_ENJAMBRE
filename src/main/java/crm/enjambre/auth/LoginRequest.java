package crm.enjambre.auth;

import crm.enjambre.model.TraceSesion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
	private String idUsuario;
	private String contrasenia;
	private TraceSesion trace;
}
