package br.net.iesb.service.transacional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.iesb.entity.transacional.Modelo;
import br.net.iesb.repository.transacional.ModeloRepository;

@Service
public class ModeloService {

	@Autowired
	ModeloRepository modeloRepository;

	public List<Modelo> getListaModelo() {
		return modeloRepository.findAll();
	}

	public List<String> getListaModeloAutoCompleteView() {
		List<Modelo> listaModelo = modeloRepository.findAll();
		List<String> listaModeloAutoCompleteView = new ArrayList<String>();
		for (Modelo modelo : listaModelo) {
			listaModeloAutoCompleteView.add(modelo.getDescricao());
		}
		return listaModeloAutoCompleteView;
	}

	public Optional<Modelo> getModeloByDescricao(String descricao) {
		return modeloRepository.findByDescricao(descricao);
	}

	public void saveModelo(Modelo modelo) {
		modeloRepository.save(modelo);
	}
}
