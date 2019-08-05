package br.net.iesb.view;

import br.net.iesb.entity.Automovel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AutomovelListaView {

	private Integer id;
	private String placa;
	private String modelo;
	private String tracao;
	private String categoria;
	private String fabricante;

	public AutomovelListaView(Automovel automovel) {
		this.id = automovel.getId();
		this.placa = automovel.getPlaca();
		this.modelo = automovel.getModelo().getDescricao();
		this.tracao = automovel.getTracao().getDescricao();
		this.categoria = automovel.getCategoria().getDescricao();
		this.fabricante = automovel.getFabricante().getNome() + "-"
				+ automovel.getFabricante().getPais().getDescricao();
	}

}
