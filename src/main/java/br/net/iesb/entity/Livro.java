package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "LIVRO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIVRO_SEQUENCE")
    @SequenceGenerator(name = "LIVRO_SEQUENCE", sequenceName = "LIVRO_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "TITULO", nullable = false)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "ID_EDITORA", nullable = false, foreignKey = @ForeignKey(name = "FK_LIVRO_EDITORA"))
    private Editora editora;

    @Column(name = "SINOPSE", nullable = false, length = 5000)
    private String sinopse;

    @ManyToOne
    @JoinColumn(name = "ID_IDIOMA", nullable = false, foreignKey = @ForeignKey(name = "FK_LIVRO_IDIOMA"))
    private Idioma idioma;

    @ManyToMany(cascade = { CascadeType.PERSIST,CascadeType.MERGE})
    @JoinTable(name = "LIVRO_AUTOR", joinColumns = @JoinColumn(name = "ID_LIVRO", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_AUTOR", referencedColumnName = "ID"))
    private List<Autor> autores;

}
