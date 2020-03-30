package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Telefone;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TelefoneResponseDTO {

    private Long id;
    private String tipo;
    private Long numero;

    public TelefoneResponseDTO(Telefone telefone){
        this.id = telefone.getId();
        this.tipo = telefone.getTipo();
        this.numero = telefone.getNumero();
    }

}
