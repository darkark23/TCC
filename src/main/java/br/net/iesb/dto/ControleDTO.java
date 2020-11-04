package br.net.iesb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ControleDTO {
    private Boolean ativo;
    private Integer aprovado;
    private String descricaoReprovado;
}
