package br.net.iesb.entity.transacional;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SITUACAO_APROVACAO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SituacaoAprovacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SITUACAO_APROVACAO_SEQUENCE")
    @SequenceGenerator(name = "SITUACAO_APROVACAO_SEQUENCE", sequenceName = "SITUACAO_APROVACAO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

}
