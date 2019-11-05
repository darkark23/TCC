package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FUNCIONALIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONALIDADE_SEQUENCE")
    @SequenceGenerator(name = "FUNCIONALIDADE_SEQUENCE", sequenceName = "FUNCIONALIDADE_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private Date descricao;

}
