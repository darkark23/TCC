package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACESSO_AUDIO_LIVRO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcessoAudioLivro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACESSO_AUDIO_LIVRO_SEQUENCE")
    @SequenceGenerator(name = "ACESSO_AUDIO_LIVRO_SEQUENCE", sequenceName = "ACESSO_AUDIO_LIVRO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DATA_ACESSO", nullable = false)
    private Date dataAcesso;

    //(IIIIII)->(HH:MM:SS)
    @Column(name = "POSICAO_AUDIO", nullable = false, length = 6)
    private Integer posicaoAudio;

    @ManyToOne
    @JoinColumn(name = "ID_AUDIO_LIVRO", foreignKey = @ForeignKey(name = "FK_ACESSO_AU_AUDLIVRO"), nullable = false)
    private AudioLivro audioLivro;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_ACESSO_AU_USUARIO"), nullable = false)
    private Usuario usuario;

}
