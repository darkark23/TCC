package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Estado;
import lombok.Getter;

@Getter
public class EstadoResponseDTO {

    private Integer id;
    private String descricao;
    private String uf;

    public EstadoResponseDTO(Estado estado){
        this.id = estado.getId();
        this.descricao = estado.getNome();
        this.uf = estado.getUf();
    }

}
