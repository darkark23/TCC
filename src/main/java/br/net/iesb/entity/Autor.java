package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "AUTOR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTOR_SEQUENCE")
    @SequenceGenerator(name = "AUTOR_SEQUENCE", sequenceName = "AUTOR_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

}
