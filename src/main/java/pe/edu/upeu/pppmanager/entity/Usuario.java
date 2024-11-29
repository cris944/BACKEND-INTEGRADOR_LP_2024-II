	package pe.edu.upeu.pppmanager.entity;
	import java.util.List;
	import java.util.Objects;
	
	import org.springframework.web.bind.annotation.CrossOrigin;
	import com.fasterxml.jackson.annotation.JsonIgnore;
	import jakarta.persistence.CascadeType;
	import jakarta.persistence.Column;
	import jakarta.persistence.Entity;
	import jakarta.persistence.FetchType;
	import jakarta.persistence.GeneratedValue;
	import jakarta.persistence.GenerationType;
	import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
	import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
	import lombok.AllArgsConstructor;
	import lombok.Data;
	import lombok.Getter;
	import lombok.NoArgsConstructor;
	import lombok.Setter;
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Setter
	@Getter
	@Entity
	@Data
	@Table(name = "usuario")
	@CrossOrigin
	public class Usuario {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id_usuario")
	    private Long id;
	    @Column(name="img_perfil")
	    private String img_perfil;
	    @Column(name = "nombre_usuario", nullable = false, unique = true,length = 100)
	    private String username;
	    @Column(name = "contrasenia",nullable = false,length = 100)
	    private String password;
		@Column(name="estado", length = 1)
		private char estado;
		
	    @OneToOne
	    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona", nullable = false)
	    private Persona persona;
	    
	    @Override
	    public int hashCode() {
	        return Objects.hash(id); 
	    }
	
	    @Override
	    public boolean equals(Object obj) {
	        if (this == obj) return true;
	        if (obj == null || getClass() != obj.getClass()) return false;
	        Usuario usuario = (Usuario) obj;
	        return Objects.equals(id, usuario.id); 
	    }
	
		
	    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	    @JsonIgnore
	    private List<Usuario_Rol> usuarioRoles;
	}