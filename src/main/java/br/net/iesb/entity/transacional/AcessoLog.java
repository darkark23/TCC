package br.net.iesb.entity.transacional;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACESSO_LOG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AcessoLog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACESSO_LOG_SEQUENCE")
    @SequenceGenerator(name = "ACESSO_LOG_SEQUENCE", sequenceName = "ACESSO_LOG_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_FUNCIONALIDADE", foreignKey = @ForeignKey(name = "FK_ACESSO_LOG_FUNCIONALIDADE"), nullable = false)
    private Funcionalidade funcionalidade;

    @ManyToOne
    @JoinColumn(name = "ID_USUARIO", foreignKey = @ForeignKey(name = "FK_ACESSO_LOG_USUARIO"), nullable = false)
    private Usuario usuario;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;

    @Column(name = "DATA_ACESSO", nullable = false)
    private Date dataAcesso;

}
