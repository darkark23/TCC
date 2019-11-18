package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FUNCIONALIDADE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Funcionalidade {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FUNCIONALIDADE_SEQUENCE")
    @SequenceGenerator(name = "FUNCIONALIDADE_SEQUENCE", sequenceName = "FUNCIONALIDADE_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
