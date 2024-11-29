package pe.edu.upeu.pppmanager.service;

import pe.edu.upeu.pppmanager.dto.SolicitudDto;

import java.util.List;

public interface SolicitudService {
    List<SolicitudDto> listarSolicitudes();
    SolicitudDto crearSolicitud(SolicitudDto solicitudDto, String token); 
    SolicitudDto obtenerSolicitudPorId(Long id);
    SolicitudDto actualizarSolicitud(Long id, SolicitudDto solicitudDto);
    void eliminarSolicitud(Long id);
    
}

