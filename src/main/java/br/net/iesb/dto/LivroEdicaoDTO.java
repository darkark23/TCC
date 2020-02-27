package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Autor;
import br.net.iesb.entity.transacional.Editora;
import br.net.iesb.entity.transacional.Idioma;
import br.net.iesb.entity.transacional.Livro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class LivroEdicaoDTO {

    private Long id;
    private String titulo;

    public LivroEdicaoDTO(Livro livro){
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

}
