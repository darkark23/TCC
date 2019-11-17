package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PESSOA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PESSOA_SEQUENCE")
    @SequenceGenerator(name = "PESSOA_SEQUENCE", sequenceName = "PESSOA_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_ENDERECO", foreignKey = @ForeignKey(name = "FK_PESSOA_ENDERECO"), nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "ID_TELEFONE", foreignKey = @ForeignKey(name = "FK_PESSOA_ENDERECO"), nullable = false)
    private Telefone telefone;

    @ManyToOne
    @JoinColumn(name = "ID_RESPONSAVEL", foreignKey = @ForeignKey(name = "FK_PESSOA_PESSOA"), nullable = false)
    private Pessoa responsavel;

    @Column(name = "SEXO", nullable = false)
    private String sexo;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "CPF", nullable = false)
    private String cpf;

    @Column(name = "RG", nullable = false)
    private String rg;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

    @OneToMany
    @JoinTable(name="PESSOA_COMPETENCIA",
            joinColumns={@JoinColumn(name="ID_COMPETENCIA", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ID_PESSOA", referencedColumnName="ID")})
    private List<Competencia> listaCompetencias;

}
