package pe.edu.upeu.pppmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@AllArgsConstructor 
@NoArgsConstructor  
@Data
public class SolicitudDto {

    private Long id;
    private char estado;
    private LocalDateTime fechaCreacion;
    private EstudianteDto estudiante;
    private EmpresaDto empresa;
    private LineaDto linea;

    @Data
    @AllArgsConstructor 
    @NoArgsConstructor 
    public static class EstudianteDto {
        private String codigo;
        private String nombre;
        private String apellido;
        private String dni;
        private String telefono;
        private String correo;
    }
    @Data
    @AllArgsConstructor 
    @NoArgsConstructor 
    public static class EmpresaDto {
        private Long id;
        private String razonSocial;
        private String direccion;
    }
    @Data
    @AllArgsConstructor 
    @NoArgsConstructor 
    public static class LineaDto {
        private Long id;
        private String nombre;
    }
}
