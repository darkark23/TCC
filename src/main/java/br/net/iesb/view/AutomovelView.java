package br.net.iesb.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.net.iesb.entity.Automovel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AutomovelView {

	private Integer id;
	private String placa;
	private String modelo;
	private Integer tracao;
	private Integer categoria;
	private Integer fabricante;

	public AutomovelView(Automovel automovel) {
		this.id = automovel.getId();
		this.placa = automovel.getPlaca();
		this.modelo = automovel.getModelo().getDescricao();
		this.tracao = automovel.getTracao().getId();
		this.categoria = automovel.getCategoria().getId();
		this.fabricante = automovel.getFabricante().getId();
	}

	public boolean verificarExistenciaCampos() {
		return placa != null && modelo != null && tracao != null && categoria != null && fabricante != null;
	}

	public boolean verificarFormatoPlaca() {
		String regex = "[A-Z]{3}[0-9]{4}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(placa.toUpperCase());
		return matcher.matches();
	}

}
