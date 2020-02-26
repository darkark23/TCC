package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Assunto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssuntoEdicaoDTO {

    private Long id;
    private String nome;

    public AssuntoEdicaoDTO(Assunto assunto){
        this.id = assunto.getId();
        this.nome = assunto.getNome();
    }

}
