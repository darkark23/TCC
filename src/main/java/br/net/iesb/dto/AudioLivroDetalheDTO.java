package br.net.iesb.dto;


import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AudioLivroDetalheDTO {

    String id;
    String tituloAudioBook;
    String ledor;
    String tituloLivroReferencia;
    List<Assunto> listaAssunto;
    String descricao;
    String url;
    String urlTexto;
    String tempo;

    public AudioLivroDetalheDTO(AudioLivro audioLivro) {
        this.id = audioLivro.getId().toString();
        this.tituloAudioBook = audioLivro.getTitulo();
        this.ledor = audioLivro.getLedor().getPessoa().getNome();
        this.tituloLivroReferencia = audioLivro.getLivroReferencia().getTitulo();
        this.listaAssunto = audioLivro.getAssuntos();
        this.descricao = audioLivro.getDescricao();
        this.url = audioLivro.getUrl();
        this.urlTexto = audioLivro.getUrlTexto();
        this.tempo = null;
    }
}
