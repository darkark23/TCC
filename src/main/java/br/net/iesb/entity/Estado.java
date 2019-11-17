package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ESTADO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ESTADO_SEQUENCE")
    @SequenceGenerator(name = "ESTADO_SEQUENCE", sequenceName = "ESTADO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "UF", nullable = false, length = 5)
    private String uf;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estado")
    private List<Cidade> listaCidade;

}
