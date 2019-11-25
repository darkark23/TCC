package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Noticia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.Date;

@Getter
public class NoticiaDTO {

    private String corpo;
    private String imagemUrl;
    private String descricaoImagem;
    private String titulo;
    private Date dataPublicacao;
    private String usuario;

    public NoticiaDTO(Noticia noticia) {
        this.corpo = noticia.getCorpo();
        this.imagemUrl = noticia.getImagem().getUrl();
        this.descricaoImagem = noticia.getImagem().getNome();
        this.titulo = noticia.getTitulo();
        this.dataPublicacao = noticia.getDataPublicacao();
        this.usuario = noticia.getUsuario().getPessoa().getNome();
    }
}
