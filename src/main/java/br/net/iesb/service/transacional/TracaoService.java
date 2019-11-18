package br.net.iesb.service.transacional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.iesb.entity.transacional.Tracao;
import br.net.iesb.repository.transacional.TracaoRepository;

@Service
public class TracaoService {
	
	@Autowired
	TracaoRepository tracaoRepository;
	
	public List<Tracao> getListaTracao() {
		return tracaoRepository.findAll();
	}
	
	public Tracao getTracaoById(Integer id) {
		return tracaoRepository.getOne(id);
	}

}
