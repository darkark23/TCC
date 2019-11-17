package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "IDIOMA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Idioma {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IDIOMA_SEQUENCE")
    @SequenceGenerator(name = "IDIOMA_SEQUENCE", sequenceName = "IDIOMA_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

}
