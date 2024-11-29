package pe.edu.upeu.pppmanager.controller;



import pe.edu.upeu.pppmanager.entity.Linea;
import pe.edu.upeu.pppmanager.service.LineaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/lineas")
@CrossOrigin(origins = "http://localhost:4200/")
public class LineaController {

    @Autowired
    private LineaService lineaService;

    @GetMapping
    public ResponseEntity<List<Linea>> readAll() {
        try {
            List<Linea> lineas = lineaService.readAll();
            if (lineas.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(lineas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Linea> create(@Valid @RequestBody Linea linea) {
        try {
            Linea newLinea = lineaService.create(linea);
            return new ResponseEntity<>(newLinea, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Linea> getLineaById(@PathVariable("id") Long id) {
        try {
            Optional<Linea> linea = lineaService.read(id);
            return linea.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLinea(@PathVariable("id") Long id) {
        try {
            lineaService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Linea> updateLinea(@PathVariable("id") Long id, @Valid @RequestBody Linea linea) {
        Optional<Linea> existingLinea = lineaService.read(id);

        if (existingLinea.isPresent()) {
            Linea updatedLinea = existingLinea.get();
            updatedLinea.setNombre(linea.getNombre());

            lineaService.update(updatedLinea);
            return new ResponseEntity<>(updatedLinea, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
