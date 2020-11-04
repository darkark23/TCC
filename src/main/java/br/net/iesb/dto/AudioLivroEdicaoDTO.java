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
public class AudioLivroEdicaoDTO {

    private String ledor;
    private String codigo;
    private Long id;
    private String tituloAudioBook;
    private List<AssuntoEdicaoDTO> listaAssunto;
    private String descricao;
    private LivroEdicaoDTO livro;
    private String url;
    private String urlTexto;
    private ControleDTO controle;
    private Integer idSituacaoAprovacao;
    private String motivoRejeicao;

    public AudioLivroEdicaoDTO(AudioLivro audioLivro) {
        this.id = audioLivro.getId();
        this.codigo = audioLivro.getCodigo();
        this.tituloAudioBook = audioLivro.getTitulo();
        listaAssunto = new ArrayList<>();
        for (Assunto assunto : audioLivro.getAssuntos()) {
            listaAssunto.add(new AssuntoEdicaoDTO(assunto));
        }
        this.descricao = audioLivro.getDescricao();
        this.livro = new LivroEdicaoDTO(audioLivro.getLivroReferencia());
        this.url = audioLivro.getUrl();
        this.urlTexto = audioLivro.getUrlTexto();
        this.idSituacaoAprovacao = audioLivro.getControle().getSituacaoAprovacao().getId();
        this.motivoRejeicao = audioLivro.getControle().getDescricaoReprovado();
    }

}
