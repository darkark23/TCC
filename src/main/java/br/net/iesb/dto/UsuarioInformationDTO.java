package br.net.iesb.dto;

import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UsuarioInformationDTO extends UsuarioLoginDTO {

    public UsuarioInformationDTO(){

    }

    public UsuarioInformationDTO (UsuarioLoginDTO usuarioLoginDTO){
        this.setNome(usuarioLoginDTO.getNome());
        this.setPerfil(usuarioLoginDTO.getPerfil());
        this.setExistente(true);
    }

}
