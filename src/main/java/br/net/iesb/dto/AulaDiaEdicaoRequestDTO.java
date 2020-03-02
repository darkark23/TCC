package br.net.iesb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class AulaDiaEdicaoRequestDTO {

    private Date data;
    private String usuario;

}
