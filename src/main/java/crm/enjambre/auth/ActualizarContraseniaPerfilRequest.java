package crm.enjambre.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActualizarContraseniaPerfilRequest {
	private String idUsuario;
	private String nuevaContrasenia;
}