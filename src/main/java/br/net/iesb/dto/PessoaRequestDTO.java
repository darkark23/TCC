package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.util.DataUtil;
import br.net.iesb.util.FormatarUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class PessoaRequestDTO {

    private Long id;
    private String nome;
    private String sexo;
    private String cpf;
    private String rg;
    private Date dataNascimento;

    private TelefoneRequestDTO telefone;
    private EnderecoRequestDTO endereco;


}
