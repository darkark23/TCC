package br.net.iesb.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.net.iesb.entity.Automovel;
import br.net.iesb.entity.Fabricante;
import br.net.iesb.entity.Modelo;
import br.net.iesb.entity.Tracao;
import br.net.iesb.json.AutomovelFiltroJson;
import br.net.iesb.repository.AutomovelRepository;
import br.net.iesb.repositoryCustom.AutomovelRepositoryCustom;
import br.net.iesb.view.AutomovelListaView;
import br.net.iesb.view.AutomovelView;

@Service
public class AutomovelService {

	@Autowired
	private AutomovelRepository automovelRepository;

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private FabricanteService fabricanteService;

	@Autowired
	private TracaoService tracaoService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private AutomovelRepositoryCustom automovelRepositoryCustom;

	public List<AutomovelListaView> getListaAutomovelView(AutomovelFiltroJson automovelFiltroJson) throws SQLException {

		List<Automovel> listaAutomovel = automovelRepositoryCustom.getListaAutomvelFiltro(automovelFiltroJson);
		List<AutomovelListaView> listaAutomovelView = new ArrayList<AutomovelListaView>();

		for (Automovel automovel : listaAutomovel) {
			listaAutomovelView.add(new AutomovelListaView(automovel));
		}
		return listaAutomovelView;

	}

	public AutomovelView getAutomovel(Integer id) {
		return new AutomovelView(automovelRepository.getOne(id));
	}

	public boolean getAutomovelByPlaca(String placa) {
		return automovelRepository.findByPlaca(placa).isPresent();
	}

	public Integer salvarAutomovel(AutomovelView automovelView) {
		automovelView.setPlaca(automovelView.getPlaca().toUpperCase());
		if (!automovelView.verificarExistenciaCampos()) {
			return 1;
		}
		if (!automovelView.verificarFormatoPlaca()) {
			return 2;
		}
		if (automovelView.getId() == null) {
			if (getAutomovelByPlaca(automovelView.getPlaca())) {
				return 3;
			} else {
				automovelRepository.saveAndFlush(criarAutomovel(automovelView));
				return 0;
			}
		} else {
			Optional<Automovel> automovelOpt = automovelRepository.findById(automovelView.getId());
			if (automovelOpt.isPresent()) {
				Optional<Automovel> automovelPlaca = automovelRepository.findByPlaca(automovelView.getPlaca());
				if (automovelPlaca.isPresent() && automovelPlaca.get().getId() != automovelView.getId()) {
					if (automovelPlaca.get().getId() != automovelView.getId()) {
						return 4;
					}
				} else {
					Automovel automovel = automovelOpt.get();
					alterarAutomovel(automovelView, automovel);
					return 0;
				}
			} else {
				return 5;
			}
		}
		return 6;
	}

	@Transactional
	public Integer removerAutomovel(Integer id) {
		try {
			automovelRepository.deleteAutomovel(id);
			return 0;
		} catch (Exception e) {
			return 1;
		}
	}

	public Automovel criarAutomovel(AutomovelView automovelView) {
		Automovel automovel = new Automovel();
		return setarAtributos(automovelView, automovel);
	}

	public void alterarAutomovel(AutomovelView automovelView, Automovel automovel) {
		automovelRepository.saveAndFlush(setarAtributos(automovelView, automovel));
	}

	public Automovel setarAtributos(AutomovelView automovelView, Automovel automovel) {
		automovel.setModelo(salvarModeloPlaca(automovelView));
		automovel.setPlaca(automovelView.getPlaca());
		automovel.setFabricante(fabricanteService.getFabricanteById(automovelView.getFabricante()));
		automovel.setCategoria(categoriaService.getCategoriaById(automovelView.getCategoria()));
		automovel.setTracao(tracaoService.getTracaoById(automovelView.getTracao()));
		return automovel;
	}

	public Modelo salvarModeloPlaca(AutomovelView automovelView) {
		Optional<Modelo> modelo = modeloService.getModeloByDescricao(automovelView.getModelo());
		if (!modelo.isPresent()) {
			modeloService.saveModelo(new Modelo(null, automovelView.getModelo()));
			return modeloService.getModeloByDescricao(automovelView.getModelo()).get();
		} else {
			return modelo.get();
		}
	}

}
