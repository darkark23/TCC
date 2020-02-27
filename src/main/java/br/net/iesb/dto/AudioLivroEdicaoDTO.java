package br.net.iesb.dto;


import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class AudioLivroEdicaoDTO {

    String ledor;
    Long id;
    String tituloAudioBook;
    List<AssuntoEdicaoDTO> listaAssunto;
    String descricao;
    LivroEdicaoDTO livro;
    String url;
    String urlTexto;

    public AudioLivroEdicaoDTO() {
    }

    public AudioLivroEdicaoDTO(AudioLivro audioLivro) {
        this.id = audioLivro.getId();
        this.tituloAudioBook = audioLivro.getTitulo();
        listaAssunto = new ArrayList<>();
        for (Assunto assunto: audioLivro.getAssuntos()) {
            listaAssunto.add(new AssuntoEdicaoDTO(assunto));
        }
        this.descricao = audioLivro.getDescricao();
        this.livro = new LivroEdicaoDTO(audioLivro.getLivroReferencia());
        this.url = audioLivro.getUrl();
        this.urlTexto = audioLivro.getUrlTexto();
    }

}
