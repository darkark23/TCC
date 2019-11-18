package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ASSUNTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Assunto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ASSUNTO_SEQUENCE")
    @SequenceGenerator(name = "ASSUNTO_SEQUENCE", sequenceName = "ASSUNTO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
