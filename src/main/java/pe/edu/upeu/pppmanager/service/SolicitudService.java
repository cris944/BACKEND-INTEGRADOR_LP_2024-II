package pe.edu.upeu.pppmanager.service;

import java.util.List;


import pe.edu.upeu.pppmanager.entity.Solicitud;

public interface SolicitudService {
	 Solicitud obtenerDatosEstudianteConEmpresasYLineas();
	    void guardarSolicitud(Solicitud solicitud);
	    List<Solicitud> listarSolicitudes();
	    List<Solicitud> listarSolicitudesPorEstudiante(Long estudianteId); 
}
    
