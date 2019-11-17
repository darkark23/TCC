package br.net.iesb.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TELEFONE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Telefone {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TELEFONE_SEQUENCE")
    @SequenceGenerator(name = "TELEFONE_SEQUENCE", sequenceName = "TELEFONE_SEQUENCE")
    @Column(name = "ID", nullable = false)
    private Long id;

    //R-RESIDENCIAL / T-TRABALHO / C-CELULAR
    @Column(name = "TIPO", nullable = false, length = 1)
    private String tipo;

    @Column(name = "NUMERO", nullable = false, length = 14)
    private Long numero;
    
}
