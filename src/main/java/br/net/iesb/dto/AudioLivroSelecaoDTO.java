package br.net.iesb.dto;


import br.net.iesb.entity.transacional.AudioLivro;
import lombok.Getter;

@Getter
public class AudioLivroSelecaoDTO {

    private String titulo;
    private String codigo;
    private String id;
    private Integer idSituacaoAprovacao;

    public AudioLivroSelecaoDTO(AudioLivro audioLivro) {
        this.titulo = audioLivro.getTitulo();
        this.codigo = audioLivro.getCodigo();
        this.id = audioLivro.getId().toString();
        this.idSituacaoAprovacao = audioLivro.getControle().getSituacaoAprovacao().getId();
    }

}
