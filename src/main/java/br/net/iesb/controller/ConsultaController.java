package br.net.iesb.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.net.iesb.entity.transacional.Categoria;
import br.net.iesb.entity.transacional.Fabricante;
import br.net.iesb.entity.transacional.Modelo;
import br.net.iesb.entity.transacional.Pais;
import br.net.iesb.entity.transacional.Tracao;
import br.net.iesb.json.AutomovelFiltroJson;
import br.net.iesb.json.FabricanteFiltroJson;
import br.net.iesb.service.transacional.AutomovelService;
import br.net.iesb.service.transacional.CategoriaService;
import br.net.iesb.service.transacional.FabricanteService;
import br.net.iesb.service.transacional.ModeloService;
import br.net.iesb.service.transacional.PaisService;
import br.net.iesb.service.transacional.TracaoService;
import br.net.iesb.view.AutomovelListaView;
import br.net.iesb.view.FabricanteListaView;

@Controller
@RequestMapping("/consultaService")
public class ConsultaController {

	@Autowired
	private AutomovelService automovelService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private FabricanteService fabricanteService;

	@Autowired
	private ModeloService modeloService;

	@Autowired
	private PaisService paisService;

	@Autowired
	private TracaoService tracaoService;

	@ResponseBody
	@GetMapping(path = "/getLista")
	public List<AutomovelListaView> getListaAutomovelView(AutomovelFiltroJson automovelFiltroJson) throws SQLException {
		return automovelService.getListaAutomovelView(automovelFiltroJson);
	}

	@ResponseBody
	@GetMapping(path = "/getListaCategoria")
	public List<Categoria> getListaCategoria() {
		return categoriaService.getListaCategoria();
	}

	@ResponseBody
	@GetMapping(path = "/getListaFabricanteView")
	public List<FabricanteListaView> getListaFabricanteView(FabricanteFiltroJson fabricanteFiltroJson) {
		return fabricanteService.getListaFabricanteView(fabricanteFiltroJson);
	}

	@ResponseBody
	@GetMapping(path = "/getFabricante/{id}")
	public Fabricante getListaFabricanteView(@PathVariable("id") Integer id) {
		return fabricanteService.getFabricanteById(id);
	}

	@ResponseBody
	@GetMapping(path = "/removerAutomovel/{id}")
	public Integer removerAutomovel(@PathVariable("id") Integer id) {
		return automovelService.removerAutomovel(id);
	}

	@ResponseBody
	@GetMapping(path = "/getListaFabricante")
	public List<Fabricante> getListaFabricante() {
		return fabricanteService.getListaFabricante();
	}

	@ResponseBody
	@GetMapping(path = "/getListaTracao")
	public List<Tracao> getListaTracao() {
		return tracaoService.getListaTracao();
	}

	@ResponseBody
	@GetMapping(path = "/getListaPais")
	public List<Pais> getListaPais() {
		return paisService.getListaPais();
	}

	@ResponseBody
	@GetMapping(path = "/getListaModelo")
	public List<Modelo> getListaModelo() {
		return modeloService.getListaModelo();
	}

	@ResponseBody
	@GetMapping(path = "/getListaModeloAutoCompleteView")
	public List<String> getListaModeloAutoCompleteView() {
		return modeloService.getListaModeloAutoCompleteView();
	}

}
