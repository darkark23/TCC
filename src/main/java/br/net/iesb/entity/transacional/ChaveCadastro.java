package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "CHAVE_CADASTRO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChaveCadastro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHAVE_CADASTRO_SEQUENCE")
    @SequenceGenerator(name = "CHAVE_CADASTRO_SEQUENCE", sequenceName = "CHAVE_CADASTRO_SEQUENCE", initialValue = 0)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CHAVE", nullable = false, length = 5)
    private Integer chave;

    @Column(name = "DATA_INSERCAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataInsercao;

    public ChaveCadastro(Integer chave){
        this.chave = chave;
        this.dataInsercao = new Date();
    }

}
