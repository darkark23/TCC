package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PERFIL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Perfil implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFIL_SEQUENCE")
    @SequenceGenerator(name = "PERFIL_SEQUENCE", sequenceName = "PERFIL_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

    @Override
    public String getAuthority() {
        return this.nome;
    }
}
