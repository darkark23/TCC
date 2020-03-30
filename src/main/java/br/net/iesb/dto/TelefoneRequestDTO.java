package br.net.iesb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class TelefoneRequestDTO {

    private Long id;
    private String tipo;
    private Long numero;

}
