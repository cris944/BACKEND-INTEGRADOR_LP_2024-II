package pe.edu.upeu.pppmanager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import pe.edu.upeu.pppmanager.dto.SolicitudDto;

import pe.edu.upeu.pppmanager.service.SolicitudService;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")  
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class SolicitudController {

    private final SolicitudService solicitudService;  


    @GetMapping
    public ResponseEntity<List<SolicitudDto>> listarSolicitudes() {
        List<SolicitudDto> solicitudes = solicitudService.listarSolicitudes();
        return new ResponseEntity<>(solicitudes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDto> obtenerSolicitudPorId(@PathVariable Long id) {
        SolicitudDto solicitudDto = solicitudService.obtenerSolicitudPorId(id);
        return new ResponseEntity<>(solicitudDto, HttpStatus.OK);
   
    }
    
    @PostMapping
    public ResponseEntity<SolicitudDto> crearSolicitud(
            @RequestBody SolicitudDto solicitudDto, @RequestHeader("Authorization") String token) {


        String jwtToken = token.substring(7); 

        SolicitudDto nuevaSolicitud = solicitudService.crearSolicitud(solicitudDto, jwtToken);
        
        return new ResponseEntity<>(nuevaSolicitud, HttpStatus.CREATED); 
    }


    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDto> actualizarSolicitud(
            @PathVariable Long id, @RequestBody SolicitudDto solicitudDto) {
        
        SolicitudDto solicitudActualizada = solicitudService.actualizarSolicitud(id, solicitudDto);
        return new ResponseEntity<>(solicitudActualizada, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarSolicitud(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
    

}
