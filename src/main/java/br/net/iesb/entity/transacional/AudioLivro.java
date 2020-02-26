package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AUDIO_LIVRO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AudioLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUDIO_LIVRO_SEQUENCE")
    @SequenceGenerator(name = "AUDIO_LIVRO_SEQUENCE", sequenceName = "AUDIO_LIVRO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITULO", nullable = false, length = 50)
    private String titulo;

    @Column(name = "CODIGO", nullable = false, length = 50)
    private String codigo;

    @Column(name = "URL_AUDIO", nullable = false, length = 255)
    private String url;

    @Column(name = "URL_TEXTO", nullable = true, length = 255)
    private String urlTexto;

    @Column(name = "DESCRICAO", nullable = false, length = 255)
    private String descricao;

    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo;

    @Column(name = "APROVADO", nullable = false)
    private Integer aprovado;

    @ManyToOne
    @JoinColumn(name = "ID_LIVRO_REFERENCIA", nullable = true, foreignKey = @ForeignKey(name = "FK_AUDIO_LIV_LIVRO"))
    private Livro livroReferencia;

    @ManyToOne
    @JoinColumn(name = "ID_LEDOR", nullable = false, foreignKey = @ForeignKey(name = "FK_AUDIO_LIV_USUARIO"))
    private Usuario ledor;

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "AUDIO_LIVRO_ASSUNTO", joinColumns = @JoinColumn(name = "ID_AUDIO_LIVRO"), inverseJoinColumns = @JoinColumn(name = "ID_ASSUNTO"))
    private List<Assunto> assuntos;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
