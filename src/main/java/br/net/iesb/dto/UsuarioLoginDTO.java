package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class  UsuarioLoginDTO {

    private String nome;
    private String login;
    private String perfil;
    private Boolean existente = false;
    private Integer situacao;
    private String motivo;

    public UsuarioLoginDTO (Usuario usuario){
        this.nome = usuario.getPessoa().getNome();
        this.perfil = usuario.getPerfil().getNome();
        this.situacao = usuario.getControle().getAprovado();
        if(situacao == 2){
            this.motivo = usuario.getControle().getDescricaoReprovado();
        }
    }

    public UsuarioLoginDTO (UsuarioInformationDTO usuarioInformationDTO){
        this.nome = usuarioInformationDTO.getNome();
        this.login = usuarioInformationDTO.getLogin();
        this.perfil = usuarioInformationDTO.getPerfil();
        this.existente = true;
        this.situacao = usuarioInformationDTO.getSituacao();
        if(situacao == 2){
            this.motivo = usuarioInformationDTO.getMotivo();
        }
    }

}
