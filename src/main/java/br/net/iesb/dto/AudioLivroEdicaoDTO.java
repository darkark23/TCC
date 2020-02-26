package br.net.iesb.dto;


import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AudioLivroEdicaoDTO {

    String id;
    String tituloAudioBook;
    String ledor;
    List<AssuntoEdicaoDTO> listaAssunto;
    String descricao;
    LivroEdicaoDTO livro;
    String url;
    String urlTexto;

    public AudioLivroEdicaoDTO(AudioLivro audioLivro) {
        this.id = audioLivro.getId().toString();
        this.tituloAudioBook = audioLivro.getTitulo();
        this.ledor = audioLivro.getLedor().getPessoa().getNome();
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
