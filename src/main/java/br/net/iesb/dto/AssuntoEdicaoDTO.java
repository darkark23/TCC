package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Assunto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssuntoEdicaoDTO {

    private Long id;
    private String nome;

    public AssuntoEdicaoDTO(Assunto assunto){
        this.id = assunto.getId();
        this.nome = assunto.getNome();
    }

}
