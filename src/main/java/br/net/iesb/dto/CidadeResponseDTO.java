package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Cidade;
import lombok.Getter;

@Getter
public class CidadeResponseDTO {

    private Integer id;
    private Integer estadoId;
    private String descricao;

    public CidadeResponseDTO(Cidade cidade){
        this.id = cidade.getId();
        this.estadoId = cidade.getEstado().getId();
        this.descricao = cidade.getNome();
    }

}
