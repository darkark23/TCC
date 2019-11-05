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
    @SequenceGenerator(name = "PERFIL_SEQUENCE", sequenceName = "PERFIL_SEQUENCE", initialValue = 10)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "DESCRICAO", nullable = false)
    private String descricao;


}
