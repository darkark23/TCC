package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Perfil;
import lombok.Getter;

@Getter
public class PerfilResponseDTO {

    private Long id;
    private String descricao;

    public  PerfilResponseDTO(Perfil perfil){
        this.id = perfil.getId();
        this.descricao = perfil.getNome();
    }

}
