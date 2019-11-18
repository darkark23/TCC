package br.net.iesb.view;

import br.net.iesb.entity.transacional.Modelo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModeloAutoCompleteView {

	private String nome;

	public ModeloAutoCompleteView(Modelo modelo) {
		this.nome = modelo.getDescricao();
	}

}
