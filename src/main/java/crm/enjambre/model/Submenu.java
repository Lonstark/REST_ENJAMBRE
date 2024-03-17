package crm.enjambre.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "submenu")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submenu {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idSubmenu;

	@Column(name = "nombre")
	private String nombre;

	@Column(name = "estado")
	private Integer estado;
    
	@ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;
    
}
