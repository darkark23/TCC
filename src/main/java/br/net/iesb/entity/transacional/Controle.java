package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CONTROLE")
@AllArgsConstructor
@Getter
@Setter
public class Controle {

    public Controle(){
        this.aprovado = 0;
        this.ativo = true;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTROLE_SEQUENCE")
    @SequenceGenerator(name = "CONTROLE_SEQUENCE", sequenceName = "CONTROLE_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ATIVO", nullable = false)
    private Boolean ativo;

    @Column(name = "APROVADO", nullable = false)
    private Integer aprovado;

    @Column(name = "DESCRICAO_REPROVADO", nullable = true, length = 255)
    private String descricaoReprovado;
}
