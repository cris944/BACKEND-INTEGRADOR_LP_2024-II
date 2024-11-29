package pe.edu.upeu.pppmanager.service;

import pe.edu.upeu.pppmanager.entity.Linea;
import java.util.List;
import java.util.Optional;

public interface LineaService {
	Linea create(Linea a);
	Linea update(Linea a);
	void delete(Long id);
    List<Linea> readAll();
    Optional<Linea> read(Long id);
}
