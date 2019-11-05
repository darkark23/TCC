package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMPETENCIA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Competencia {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPETENCIA_SEQUENCE")
    @SequenceGenerator(name = "COMPETENCIA_SEQUENCE", sequenceName = "COMPETENCIA_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @OneToMany
    @JoinTable(name="PESSOA_COMPETENCIA",
            joinColumns={@JoinColumn(name="ID_PESSOA", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ID_COMPETENCIA", referencedColumnName="ID")})
    private List<Pessoa> listaPessoas;

}
