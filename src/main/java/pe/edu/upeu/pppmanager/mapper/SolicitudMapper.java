package pe.edu.upeu.pppmanager.mapper;

import pe.edu.upeu.pppmanager.dto.SolicitudDto;
import pe.edu.upeu.pppmanager.entity.Solicitud;

public class SolicitudMapper {

    public static SolicitudDto toDto(Solicitud solicitud) {
        SolicitudDto dto = new SolicitudDto();
        dto.setId(solicitud.getId());
        dto.setEstado(solicitud.getEstado());
        dto.setFechaCreacion(solicitud.getFechaCreacion());


        SolicitudDto.EstudianteDto estudianteDto = new SolicitudDto.EstudianteDto();
        estudianteDto.setCodigo(solicitud.getEstudiante().getCodigo());
        estudianteDto.setNombre(solicitud.getEstudiante().getPersona().getNombre());
        estudianteDto.setApellido(solicitud.getEstudiante().getPersona().getApellido());
        estudianteDto.setDni(solicitud.getEstudiante().getPersona().getDni());
        estudianteDto.setTelefono(solicitud.getEstudiante().getPersona().getTelefono());
        estudianteDto.setCorreo(solicitud.getEstudiante().getPersona().getCorreo());
        dto.setEstudiante(estudianteDto);


        SolicitudDto.EmpresaDto empresaDto = new SolicitudDto.EmpresaDto();
        empresaDto.setId(solicitud.getEmpresa().getId());
        empresaDto.setRazonSocial(solicitud.getEmpresa().getRazon_Social());
        empresaDto.setDireccion(solicitud.getEmpresa().getDireccion());
        dto.setEmpresa(empresaDto);


        SolicitudDto.LineaDto lineaDto = new SolicitudDto.LineaDto();
        lineaDto.setId(solicitud.getLinea().getId());
        lineaDto.setNombre(solicitud.getLinea().getNombre());
        dto.setLinea(lineaDto);

        return dto;
    }
}

