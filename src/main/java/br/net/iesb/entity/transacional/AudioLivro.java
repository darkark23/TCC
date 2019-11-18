package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "URL_AUDIO", nullable = false, length = 255)
    private String url;

    @ManyToOne
    @JoinColumn(name = "ID_LIVRO_REFERENCIA", nullable = false, foreignKey = @ForeignKey(name = "FK_AUDIO_LIV_LIVRO"))
    private Livro livroReferencia;

    @ManyToOne
    @JoinColumn(name = "ID_LEDOR", nullable = false, foreignKey = @ForeignKey(name = "FK_AUDIO_LIV_USUARIO"))
    private Usuario ledor;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
