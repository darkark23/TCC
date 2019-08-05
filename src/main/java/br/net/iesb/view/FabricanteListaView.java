package br.net.iesb.view;

import br.net.iesb.entity.Fabricante;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FabricanteListaView {

	private Integer id;
	private String nome;
	private String pais;

	public FabricanteListaView(Fabricante fabricante) {
		this.id = fabricante.getId();
		this.nome = fabricante.getNome();
		this.pais = fabricante.getPais().getDescricao();
	}

}
