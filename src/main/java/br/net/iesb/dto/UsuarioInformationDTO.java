package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
@Setter
@Getter
public class UsuarioInformationDTO extends UsuarioLoginDTO {

    public UsuarioInformationDTO(){}

    public UsuarioInformationDTO (Usuario usuario){
        this.setLogin(usuario.getLogin());
        this.setNome(usuario.getPessoa().getNome());
        this.setPerfil(usuario.getPerfil().getNome());
        this.setExistente(true);
    }

}
