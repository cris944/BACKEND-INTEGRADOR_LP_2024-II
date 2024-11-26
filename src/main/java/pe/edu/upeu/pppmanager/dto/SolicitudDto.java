package pe.edu.upeu.pppmanager.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

@Data
public class SolicitudDto {
    private Long id; 
    private String estado; 
    private LocalDateTime fechaCreacion; 
    private Estudiante estudiante; 
    private Empresa empresa;
    private Linea lineaCarrera; 
    private List<Empresa> empresas; 
    private List<Linea> lineasCarrera; 

    @Data
    public static class Estudiante {
        private String codigo;
        private String nombre; 
        private String apellido;
        private String dni;
        private String telefono;
        private String correo; 
    }

    @Data
    public static class Empresa {
        private Long id; 
        private String razonSocial; 
        private String direccion; 
        private String email; 
        private String telefono; 
    }

    @Data
    public static class Linea {
        private Long id;
        private String nombre; 
    }
}