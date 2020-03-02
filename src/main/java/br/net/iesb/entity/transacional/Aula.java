package br.net.iesb.entity.transacional;

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
    @SequenceGenerator(name = "AULA_SEQUENCE", sequenceName = "AULA_SEQUENCE",initialValue = 5)
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

    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ID_LEDOR", nullable = false, foreignKey = @ForeignKey(name = "FK_AULA_USUARIO") )
    private Usuario ledor;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ID_CONTROLE", nullable = false, foreignKey = @ForeignKey(name = "FK_AULA_CONTROLE") )
    private Controle controle;

}
