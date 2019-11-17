package br.net.iesb.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ENDERECO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQUENCE")
    @SequenceGenerator(name = "ENDERECO_SEQUENCE", sequenceName = "ENDERECO_SEQUENCE", initialValue = 10)

    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_CIDADE", foreignKey = @ForeignKey(name = "FK_ENDERECO_CIDADE"), nullable = false)
    private Cidade cidade;

    @Column(name = "BAIRRO", nullable = false)
    private String bairro;

    @Column(name = "COMPLEMENTO", nullable = false)
    private String complemento;

    @Column(name = "CEP", nullable = false)
    private String cep;

}
