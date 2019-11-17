package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "USUARIO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Usuario {

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

    @Column(name = "SENHA", nullable = false, length = 50)
    private String senha;

}
