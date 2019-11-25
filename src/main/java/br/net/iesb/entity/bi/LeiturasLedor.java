package br.net.iesb.entity.bi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LEITURAS_LEDOR")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeiturasLedor {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEITURAS_LEDOR_SEQUENCE")
	@SequenceGenerator(name = "LEITURAS_LEDOR_SEQUENCE", sequenceName = "LEITURAS_LEDOR_SEQUENCE")
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "SEXO", nullable = false, length = 50)
	private String sexo;

	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;

	@Column(name = "IDADE", nullable = false, length = 3)
	private Integer idade;

}
