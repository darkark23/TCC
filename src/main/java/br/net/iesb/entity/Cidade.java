package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CIDADE_SEQUENCE")
    @SequenceGenerator(name = "CIDADE_SEQUENCE", sequenceName = "CIDADE_SEQUENCE", initialValue = 10)

    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "ID_ESTADO", foreignKey = @ForeignKey(name = "FK_CIDADE_ESTADO"), nullable = false)
    private Estado estado;

}
