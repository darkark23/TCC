package br.net.iesb.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class AulaRequestDTO {

    private String login;
    private AssuntoEdicaoDTO assunto;
    private String titulo;
    private String descricao;
    private Integer idHorario;
    private Date data;

}
