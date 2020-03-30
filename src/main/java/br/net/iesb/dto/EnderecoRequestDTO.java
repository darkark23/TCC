package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Cidade;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
public class EnderecoRequestDTO {

    private Long id;
    private Long cidadeId;
    private String bairro;
    private String complemento;
    private Integer cep;

}
