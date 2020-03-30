package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Endereco;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class EnderecoResponseDTO {

    private Long id;
    private Integer cidadeId;
    private Integer estadoId;
    private String bairro;
    private String complemento;
    private Integer cep;

    public EnderecoResponseDTO(Endereco endereco){
        this.id = endereco.getId();
        this.cidadeId = endereco.getCidade().getId();
        this.estadoId = endereco.getCidade().getEstado().getId();
        this.bairro = endereco.getBairro();
        this.complemento = endereco.getComplemento();
        this.cep = endereco.getCep();
    }

}
