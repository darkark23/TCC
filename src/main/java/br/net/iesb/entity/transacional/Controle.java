package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CONTROLE")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Controle {

    public Controle(SituacaoAprovacao situacaoAprovacao){
        this.situacaoAprovacao = situacaoAprovacao;
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLE_SEQUENCE")
    @SequenceGenerator(name = "CONTROLE_SEQUENCE", sequenceName = "CONTROLE_SEQUENCE",initialValue = 16)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo;

    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ID_SITUACAO_APROVACAO", nullable = false, foreignKey = @ForeignKey(name = "FK_CONTROLE_SITUACAO"))
    private SituacaoAprovacao situacaoAprovacao;

    @Column(name = "DESCRICAO_REPROVADO", nullable = true, length = 255)
    private String descricaoReprovado;

}
