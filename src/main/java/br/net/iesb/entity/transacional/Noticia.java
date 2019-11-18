package br.net.iesb.entity.transacional;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTICIA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Noticia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "NOTICIA_SEQUENCE")
    @SequenceGenerator(name = "NOTICIA_SEQUENCE", sequenceName = "NOTICIA_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_NOTICIA_USUARIO"), nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "ID_IMAGEM", foreignKey = @ForeignKey(name = "FK_NOTICIA_IMAGEM"), nullable = false)
    private Imagem imagem;

    @Column(name = "DATA_PUBLICACAO", nullable = false)
    private Date dataPublicacao;

    @Column(name = "TITULO", nullable = false, length = 50)
    private String titulo;

    @Column(name = "CORPO", nullable = false, length = 4000)
    private String corpo;

}
