package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.enumeration.SituacaoAprovacaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsuarioDTOResponse {

    private Long id;
    private String usuario;
    private String nomePessoa;
    private Integer situacaoId;
    private Long perfilId;
    private String perfilNome;
    private Integer idSituacaoAprovacao;

    public UsuarioDTOResponse(Usuario usuario){
        this.id = usuario.getId();
        this.usuario = usuario.getLogin();
        this.nomePessoa = usuario.getPessoa().getNome();
        this.situacaoId = usuario.getControle().getSituacaoAprovacao().getId();
        this.perfilNome = usuario.getPerfil().getNome();
        this.perfilId = usuario.getPerfil().getId();
        this.idSituacaoAprovacao =  usuario.getControle().getSituacaoAprovacao().getId();
    }

}
