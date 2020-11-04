package br.net.iesb.dto;


import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AudioLivroDTO {

    private String ledor;
    private Long id;
    private String tituloAudioBook;
    private List<AssuntoEdicaoDTO> listaAssunto;
    private String descricao;
    private LivroEdicaoDTO livro;
    private String url;
    private String urlTexto;
    private ControleDTO controle;


}
