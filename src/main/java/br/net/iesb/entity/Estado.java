package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ESTADO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_SEQUENCE")
    @SequenceGenerator(name = "ESTADO_SEQUENCE", sequenceName = "ESTADO_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

}
