package pe.edu.upeu.pppmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pe.edu.upeu.pppmanager.entity.Solicitud;



public interface SolicitudRepository extends JpaRepository<Solicitud,Long>{
	List<Solicitud> findByEstudianteId(Long estudianteId);
    List<Solicitud> findByEstado(String estado);

}
