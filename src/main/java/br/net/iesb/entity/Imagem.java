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
    @SequenceGenerator(name = "IMAGEM_SEQUENCE", sequenceName = "IMAGEM_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "URL", nullable = false)
    private String url;

}
