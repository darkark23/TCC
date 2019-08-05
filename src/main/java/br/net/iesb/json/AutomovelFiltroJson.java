package br.net.iesb.json;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutomovelFiltroJson {

	private String placa = "";
	private String modelo = "";
	private Integer fabricante;
	private Integer tracao;
	private Integer categoria;
	
}
