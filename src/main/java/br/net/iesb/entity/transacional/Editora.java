package br.net.iesb.entity.transacional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "EDITORA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EDITORA_SEQUENCE")
    @SequenceGenerator(name = "EDITORA_SEQUENCE", sequenceName = "EDITORA_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "editora")
    private List<Livro> listaLivros;

    @Column(name = "Nome", nullable = false, length = 50)
    private String nome;

    @Column(name = "DATA_INSERCAO", nullable = false)
    private Date dataInsercao;

}
