package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

}
