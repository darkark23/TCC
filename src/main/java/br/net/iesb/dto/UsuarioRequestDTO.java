package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Usuario;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UsuarioRequestDTO {

    private Long id;
    private String Motivo;
    private Long perfil;
    private String login;
    private String email;
    private String senha;

    private PessoaRequestDTO pessoa;

}
