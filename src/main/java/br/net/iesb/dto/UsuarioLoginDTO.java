package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.enumeration.SituacaoAprovacaoEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class  UsuarioLoginDTO {

    private Long id;
    private String nome;
    private String login;
    private String perfil;
    private Boolean existente = false;
    private Integer idSituacaoAprovacao;
    private String motivo;

    public UsuarioLoginDTO (Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getPessoa().getNome();
        this.perfil = usuario.getPerfil().getNome();
        this.idSituacaoAprovacao = usuario.getControle().getSituacaoAprovacao().getId();
        if(idSituacaoAprovacao == SituacaoAprovacaoEnum.REPROVADO.getId()){
            this.motivo = usuario.getControle().getDescricaoReprovado();
        }
    }

    public UsuarioLoginDTO (UsuarioInformationDTO usuarioInformationDTO){
        this.nome = usuarioInformationDTO.getNome();
        this.login = usuarioInformationDTO.getLogin();
        this.perfil = usuarioInformationDTO.getPerfil();
        this.existente = true;
        this.idSituacaoAprovacao = usuarioInformationDTO.getIdSituacaoAprovacao();
        if(idSituacaoAprovacao == SituacaoAprovacaoEnum.REPROVADO.getId()){
            this.motivo = usuarioInformationDTO.getMotivo();
        }
    }

}
