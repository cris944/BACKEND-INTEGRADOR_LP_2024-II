package pe.edu.upeu.pppmanager.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.pppmanager.entity.Linea;
import pe.edu.upeu.pppmanager.repository.LineaRepository;
import pe.edu.upeu.pppmanager.service.LineaService;

import java.util.List;
import java.util.Optional;

@Service
public class LineaServiceImpl implements LineaService {

    private final LineaRepository lineaRepository;

    @Autowired
    public LineaServiceImpl(LineaRepository lineaRepository) {
        this.lineaRepository = lineaRepository;
    }

	@Override
	public Linea create(Linea a) {
		// TODO Auto-generated method stub
		return lineaRepository.save(a);
	}

	@Override
	public Linea update(Linea a) {
		// TODO Auto-generated method stub
		return lineaRepository.save(a);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		lineaRepository.deleteById(id);	
	}

	@Override
	public List<Linea> readAll() {
		// TODO Auto-generated method stub
        return lineaRepository.findAll();
	}

	@Override
	public Optional<Linea> read(Long id) {
		// TODO Auto-generated method stub
        return lineaRepository.findById(id);
	}
}
