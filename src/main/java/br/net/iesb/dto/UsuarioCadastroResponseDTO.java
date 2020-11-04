package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsuarioCadastroResponseDTO {

    private Long id;
    private String motivoRejeicao;
    private Long perfil;
    private String login;
    private String email;
    private String senha;
    private Integer idSituacaoAprovacao;
    private PessoaResponseDTO pessoa;

    public UsuarioCadastroResponseDTO(Usuario usuario){
        this.id = usuario.getId();
        this.perfil =usuario.getPerfil().getId();
        this.login = usuario.getLogin();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.pessoa = new PessoaResponseDTO(usuario.getPessoa());
        this.idSituacaoAprovacao = usuario.getControle().getSituacaoAprovacao().getId();
        this.motivoRejeicao = usuario.getControle().getDescricaoReprovado();
    }
}
