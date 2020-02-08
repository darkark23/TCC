package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Autor;
import br.net.iesb.entity.transacional.Editora;
import br.net.iesb.entity.transacional.Idioma;
import br.net.iesb.entity.transacional.Livro;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
public class LivroNovoDTO {

    private Long id;
    private String titulo;
    private Long editora;
    private String sinopse;
    private Long idioma;
    private List<Long> autores;

}
