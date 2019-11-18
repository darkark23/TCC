package br.net.iesb.entity.bi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LEITURAS_IDIOMA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeiturasIdioma {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEITURAS_IDIOMA_SEQUENCE")
	@SequenceGenerator(name = "LEITURAS_IDIOMA_SEQUENCE", sequenceName = "LEITURAS_IDIOMA_SEQUENCE")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "NOME", nullable = false, length = 50)
	private String nome;

}
