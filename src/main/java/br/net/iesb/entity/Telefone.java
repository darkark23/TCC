package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TELEFONE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TELEFONE_SEQUENCE")
    @SequenceGenerator(name = "TELEFONE_SEQUENCE", sequenceName = "TELEFONE_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "TIPO", nullable = false)
    private String tipo;

    @Column(name = "NUMERO", nullable = false)
    private String numero;
    
}
