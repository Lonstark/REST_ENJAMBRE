package crm.enjambre.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseArchivo {
	private String name;
	private String url;
	private String type;
	private String size;
	private String fecha;
}
