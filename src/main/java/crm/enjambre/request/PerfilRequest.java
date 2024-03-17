package crm.enjambre.request;

import java.util.List;

import crm.enjambre.model.Perfil;
import crm.enjambre.model.PerfilAccion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilRequest {
	private Perfil perfil;
	private List<PerfilAccion> perfilAccion;
}
