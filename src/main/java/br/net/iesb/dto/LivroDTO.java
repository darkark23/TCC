package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Autor;
import br.net.iesb.entity.transacional.Editora;
import br.net.iesb.entity.transacional.Idioma;
import br.net.iesb.entity.transacional.Livro;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@Getter
public class LivroDTO {

    private Long id;
    private String titulo;
    private Editora editora;
    private String sinopse;
    private Idioma idioma;
    private List<Autor> autores;

    public LivroDTO(Livro livro){
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.editora = livro.getEditora();
        this.sinopse = livro.getSinopse();
        this.idioma = livro.getIdioma();
        this.autores = livro.getAutores();
    }

}
