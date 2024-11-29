package pe.edu.upeu.pppmanager.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pe.edu.upeu.pppmanager.config.JwtUtils;
import pe.edu.upeu.pppmanager.dto.SolicitudDto;
import pe.edu.upeu.pppmanager.entity.Empresa;
import pe.edu.upeu.pppmanager.entity.Estudiante;
import pe.edu.upeu.pppmanager.entity.Linea;
import pe.edu.upeu.pppmanager.entity.Persona;
import pe.edu.upeu.pppmanager.entity.Solicitud;
import pe.edu.upeu.pppmanager.entity.Usuario;
import pe.edu.upeu.pppmanager.mapper.SolicitudMapper;
import pe.edu.upeu.pppmanager.repository.EmpresaRepository;
import pe.edu.upeu.pppmanager.repository.LineaRepository;
import pe.edu.upeu.pppmanager.repository.SolicitudRepository;
import pe.edu.upeu.pppmanager.repository.UsuarioRepository;
import pe.edu.upeu.pppmanager.service.SolicitudService;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final EmpresaRepository empresaRepository;
    private final LineaRepository lineaRepository;
    private final UsuarioRepository usuarioRepository;
    private final JwtUtils jwtUtils;

    @Override
    @Transactional
    public SolicitudDto crearSolicitud(SolicitudDto solicitudDto, String token) {
        if (solicitudDto == null || solicitudDto.getEmpresa() == null || solicitudDto.getLinea() == null) {
            throw new IllegalArgumentException("La solicitud no contiene los datos necesarios.");
        }


        String username = jwtUtils.extractUsername(token);
        

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado: " + username));


        Persona persona = usuario.getPersona();
        if (persona == null) {
            throw new EntityNotFoundException("Persona no encontrada para el usuario: " + username);
        }


        Set<Estudiante> estudiantes = persona.getEstudiante();
        if (estudiantes == null || estudiantes.isEmpty()) {
            throw new EntityNotFoundException("No hay Estudiantes asociados a esta Persona.");
        }


        Estudiante estudiante = estudiantes.iterator().next();
        if (estudiante == null) {
            throw new EntityNotFoundException("Estudiante no asociado a la persona: " + persona.getId());
        }
        
        SolicitudDto.EstudianteDto estudianteDto = new SolicitudDto.EstudianteDto();
        estudianteDto.setNombre(persona.getNombre());
        estudianteDto.setApellido(persona.getApellido());
        estudianteDto.setCodigo(estudiante.getCodigo());
        estudianteDto.setDni(persona.getDni());
        estudianteDto.setTelefono(persona.getTelefono());
        estudianteDto.setCorreo(persona.getCorreo());

        Empresa empresa = empresaRepository.findById(solicitudDto.getEmpresa().getId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con ID: " + solicitudDto.getEmpresa().getId()));

        Linea linea = lineaRepository.findById(solicitudDto.getLinea().getId())
                .orElseThrow(() -> new EntityNotFoundException("Línea no encontrada con ID: " + solicitudDto.getLinea().getId()));


        Solicitud solicitud = new Solicitud();
        solicitud.setEstudiante(estudiante);
        solicitud.setEmpresa(empresa);
        solicitud.setLinea(linea);
        solicitud.setEstado('P');  
        solicitud.setFechaCreacion(LocalDateTime.now());


        Solicitud solicitudGuardada = solicitudRepository.save(solicitud);


        return SolicitudMapper.toDto(solicitudGuardada);
    }

    @Override
    public List<SolicitudDto> listarSolicitudes() {
        return solicitudRepository.findAll().stream()
                .map(solicitud -> new SolicitudDto(
                        solicitud.getId(),
                        solicitud.getEstado(),
                        solicitud.getFechaCreacion(),
                        new SolicitudDto.EstudianteDto(solicitud.getEstudiante().getCodigo(),
                                                       solicitud.getEstudiante().getPersona().getNombre(),
                                                       solicitud.getEstudiante().getPersona().getApellido(),
                                                       solicitud.getEstudiante().getPersona().getDni(),
                                                       solicitud.getEstudiante().getPersona().getTelefono(),
                                                       solicitud.getEstudiante().getPersona().getCorreo()),
                        new SolicitudDto.EmpresaDto(solicitud.getEmpresa().getId(),
                                                    solicitud.getEmpresa().getRazon_Social(),
                                                    solicitud.getEmpresa().getDireccion()),
                        new SolicitudDto.LineaDto(solicitud.getLinea().getId(),
                                                   solicitud.getLinea().getNombre())
                ))
                .collect(Collectors.toList());
    }


    @Override
    public SolicitudDto obtenerSolicitudPorId(Long id) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id);

        if (solicitud.isEmpty()) {
            throw new EntityNotFoundException("Solicitud no encontrada con ID: " + id);
        }
        return SolicitudMapper.toDto(solicitud.get());
    }

    @Override
    @Transactional
    public SolicitudDto actualizarSolicitud(Long id, SolicitudDto solicitudDto) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada con ID: " + id));

        char nuevoEstado = solicitudDto.getEstado();
        if (nuevoEstado != 'I' && nuevoEstado != 'A' && nuevoEstado != 'P') { 
            throw new IllegalArgumentException("Estado inválido: " + nuevoEstado);
        }


        solicitud.setEstado(nuevoEstado);
        Solicitud updatedSolicitud = solicitudRepository.save(solicitud);

        return SolicitudMapper.toDto(updatedSolicitud);
    }

    @Override
    @Transactional
    public void eliminarSolicitud(Long id) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Solicitud no encontrada con ID: " + id));

        solicitudRepository.delete(solicitud);
    }
}
