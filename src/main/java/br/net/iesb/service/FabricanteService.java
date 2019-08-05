package br.net.iesb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.iesb.entity.Categoria;
import br.net.iesb.entity.Fabricante;
import br.net.iesb.json.FabricanteFiltroJson;
import br.net.iesb.repository.FabricanteRepository;
import br.net.iesb.repository.PaisRepository;
import br.net.iesb.view.FabricanteListaView;

@Service
public class FabricanteService {

	@Autowired
	FabricanteRepository fabricanteRepository;

	@Autowired
	PaisRepository paisRepository;

	public List<Fabricante> getListaFabricante() {
		return fabricanteRepository.findAll();
	}

	public Fabricante getFabricanteById(Integer id) {
		if (fabricanteRepository.findById(id).isPresent()) {
			return fabricanteRepository.findById(id).get();
		}
		return null;
	}

	public Integer salvarFabricante(FabricanteFiltroJson fabricanteFiltroJson) {
		Fabricante fabricante = new Fabricante();
		fabricante.setNome(fabricanteFiltroJson.getNome());
		fabricante.setPais(paisRepository.getOne(fabricanteFiltroJson.getPais()));
		Integer id = fabricanteRepository.saveAndFlush(fabricante).getId();
		return id;
	}

	public List<FabricanteListaView> getListaFabricanteView(FabricanteFiltroJson fabricanteFiltroJson) {

		List<Fabricante> listaFabricante = new ArrayList<Fabricante>();

		if (fabricanteFiltroJson.getPais() == null
				&& (fabricanteFiltroJson.getNome() == null || fabricanteFiltroJson.getNome().equals(""))) {
			listaFabricante = fabricanteRepository.findAll();
		} else if (fabricanteFiltroJson.getPais() == null) {
			listaFabricante = fabricanteRepository.findByNome(fabricanteFiltroJson.getNome());
		} else if (fabricanteFiltroJson.getNome() == null) {
			listaFabricante = fabricanteRepository.findByPaisId(fabricanteFiltroJson.getPais());
		} else {
			listaFabricante = fabricanteRepository.findByNomeAndByPais(fabricanteFiltroJson.getNome(),
					fabricanteFiltroJson.getPais());
		}

		fabricanteRepository.findAll();
		List<FabricanteListaView> listaFabricanteView = new ArrayList<FabricanteListaView>();

		for (Fabricante fabricante : listaFabricante) {
			listaFabricanteView.add(new FabricanteListaView(fabricante));
		}
		return listaFabricanteView;
	}

}
