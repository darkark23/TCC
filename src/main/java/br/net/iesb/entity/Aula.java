package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AULA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AULA_SEQUENCE")
    @SequenceGenerator(name = "AULA_SEQUENCE", sequenceName = "AULA_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 4000)
    private String descricao;

    @Column(name = "DATA_HORARIO_AULA", nullable = false)
    private Date dataHorario;

    @ManyToOne
    @JoinColumn(name = "ID_ASSUNTO", nullable = false, foreignKey = @ForeignKey(name = "FK_AULA_ASSUNTO") )
    private Assunto Assunto;

    @ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "AULA_LEDOR", joinColumns = @JoinColumn(name = "ID_AULA"), inverseJoinColumns = @JoinColumn(name = "ID_LEDOR"))
    private List<Usuario> usuarios;

}
