package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "AUTOR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTOR_SEQUENCE")
    @SequenceGenerator(name = "AUTOR_SEQUENCE", sequenceName = "AUTOR_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "NOME", nullable = false, length = 50)
    private String nome;

    @Column(name = "DATA_NASCIMENTO", nullable = false)
    private Date dataNascimento;

    @ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "LIVRO_AUTOR", joinColumns = @JoinColumn(name = "ID_AUTOR", referencedColumnName = "ID")
            , inverseJoinColumns = @JoinColumn(name = "ID_LIVRO", referencedColumnName = "ID"))
    private List<Livro> listaLivros;

}
