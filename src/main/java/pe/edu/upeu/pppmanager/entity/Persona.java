package pe.edu.upeu.pppmanager.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Data
@Table(name = "persona")
@CrossOrigin
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Long id;

    @Column(name = "apellido", length = 100)
    private String apellido;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "dni", length = 8, nullable = false, unique = true)
    private String dni;

    @Column(name = "correo", length = 50, unique = true)
    private String correo;
    
    @Column(name = "pais", length = 100)
    private String pais;
    
    @Column(name = "religion", length = 100)
    private String religion;
    
    @Column(name = "telefono", length = 12)
    private String telefono;

    @Column(name = "estado", length = 1)
    private char estado;
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy ="persona",orphanRemoval = true)
	@JsonIgnore
	private Set<Estudiante> estudiante;
	
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
    @JsonIgnore
    private Usuario usuario;


    @Override
    public int hashCode() {
        return Objects.hash(id);  
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Persona persona = (Persona) obj;
        return Objects.equals(id, persona.id);  
    }
}



