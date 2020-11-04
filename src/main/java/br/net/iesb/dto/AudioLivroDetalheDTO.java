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
public class AudioLivroDetalheDTO {

    private String id;
    private String tituloAudioBook;
    private String ledor;
    private String tituloLivroReferencia;
    private List<Assunto> listaAssunto;
    private String descricao;
    private String url;
    private String urlTexto;
    private String tempo;
    private Integer idSituacaoAprovacao;
    private String motivoRejeicao;

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
        this.idSituacaoAprovacao = audioLivro.getControle().getSituacaoAprovacao().getId();
        this.motivoRejeicao = audioLivro.getControle().getDescricaoReprovado();
    }
}
