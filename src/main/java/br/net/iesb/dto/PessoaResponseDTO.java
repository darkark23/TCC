package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Endereco;
import br.net.iesb.entity.transacional.Pessoa;
import br.net.iesb.entity.transacional.Telefone;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class PessoaResponseDTO {

    private Long id;
    private String nome;
    private String sexo;
    private Long cpf;
    private Integer rg;
    private Date dataNascimento;

    private TelefoneResponseDTO telefone;
    private EnderecoResponseDTO endereco;

    PessoaResponseDTO(Pessoa pessoa){
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.sexo = pessoa.getSexo();
        this.cpf = pessoa.getCpf();
        this.rg = pessoa.getRg();
        this.dataNascimento = pessoa.getDataNascimento();

        this.telefone = new TelefoneResponseDTO(pessoa.getTelefone());
        this.endereco = new EnderecoResponseDTO(pessoa.getEndereco());
    }

}
