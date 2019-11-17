package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PERFIL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERFIL_SEQUENCE")
    @SequenceGenerator(name = "PERFIL_SEQUENCE", sequenceName = "PERFIL_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false, length = 100)
    private String descricao;

}
