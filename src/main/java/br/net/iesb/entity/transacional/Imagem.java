package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "IMAGEM")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IMAGEM_SEQUENCE")
    @SequenceGenerator(name = "IMAGEM_SEQUENCE", sequenceName = "IMAGEM_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "DESCRICAO", nullable = false, length = 255)
    private String nome;

    @Column(name = "URL", nullable = false, length = 255)
    private String url;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
