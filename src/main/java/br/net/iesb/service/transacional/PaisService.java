package br.net.iesb.service.transacional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.iesb.entity.transacional.Pais;
import br.net.iesb.repository.transacional.PaisRepository;

@Service
public class PaisService {

	@Autowired
	PaisRepository paisRepository;

	public List<Pais> getListaPais() {
		return paisRepository.findAll();
	}

}
