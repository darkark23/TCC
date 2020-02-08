package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class  UsuarioLoginDTO {

    private String nome;
    private String perfil;
    private Boolean existente = false;

    public UsuarioLoginDTO (Usuario usuario){
        this.nome = usuario.getPessoa().getNome();
        this.perfil = usuario.getPerfil().getNome();
        this.existente = true;
    }

}
