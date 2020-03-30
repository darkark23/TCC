package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_SEQUENCE")
    @SequenceGenerator(name = "USUARIO_SEQUENCE", sequenceName = "USUARIO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_PESSOA", foreignKey = @ForeignKey(name = "FK_USUARIO_PESSOA"), nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "ID_PERFIL", foreignKey = @ForeignKey(name = "FK_USUARIO_PERFIL"), nullable = false)
    private Perfil perfil;

    @Column(name = "LOGIN", nullable = false, length = 50)
    private String login;

    @Column(name = "EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "SENHA", nullable = false, length = 60)
    private String senha;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

    @ManyToOne(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinColumn(name = "ID_CONTROLE", nullable = false, foreignKey = @ForeignKey(name = "FK_AULA_CONTROLE") )
    private Controle controle;

    @OneToMany
    @JoinColumn(name = "ID_LEDOR")
    private List<Competencia> listaCompetencias;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Perfil> perfils = new ArrayList<>();
        perfils.add(this.perfil);
        return perfils;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.controle.getAtivo();
    }

    @Override
    public boolean isAccountNonLocked() {
        return controle.getAtivo();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return controle.getAtivo();
    }

    @Override
    public boolean isEnabled() {
        return controle.getAtivo();
    }
}
