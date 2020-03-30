package br.net.iesb.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class AulaRequestDTO {

    private Long id;
    private String login;
    private AssuntoEdicaoDTO assunto;
    private String titulo;
    private String descricao;
    private Integer idHorario;
    private String motivo;
    private Date data;

}
