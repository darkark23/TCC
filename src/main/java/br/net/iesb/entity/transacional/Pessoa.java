package br.net.iesb.entity.transacional;

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
    @SequenceGenerator(name = "PESSOA_SEQUENCE", sequenceName = "PESSOA_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_ENDERECO", foreignKey = @ForeignKey(name = "FK_PESSOA_ENDERECO"), nullable = false)
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "ID_TELEFONE", foreignKey = @ForeignKey(name = "FK_PESSOA_TELEFONE"), nullable = false)
    private Telefone telefone;

    @ManyToOne
    @JoinColumn(name = "ID_RESPONSAVEL", foreignKey = @ForeignKey(name = "FK_PESSOA_PESSOA"), nullable = true)
    private Pessoa responsavel;

    @Column(name = "SEXO", nullable = false, length = 1)
    private String sexo;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "CPF", nullable = false, length = 11)
    private Long cpf;

    @Column(name = "RG", nullable = false, length = 9)
    private Integer rg;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

    @ManyToMany
    @JoinTable(name="PESSOA_COMPETENCIA",
            joinColumns={@JoinColumn(name="ID_PESSOA", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="ID_COMPETENCIA", referencedColumnName="ID")})
    private List<Competencia> listaCompetencias;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
