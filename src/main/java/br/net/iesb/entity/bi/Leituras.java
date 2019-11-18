package br.net.iesb.entity.bi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "LEITURAS")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Leituras {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTOMOVEL_SEQUENCE")
	@SequenceGenerator(name = "AUTOMOVEL_SEQUENCE", sequenceName = "AUTOMOVEL_SEQUENCE")
	@Column(name = "ID", nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_LEITOR", foreignKey = @ForeignKey(name = "FK_LEITURAS_LEITOR"), nullable = false)
	private LeiturasUsuario leitor;

	@ManyToOne
	@JoinColumn(name = "ID_LEDOR", foreignKey = @ForeignKey(name = "FK_LEITURAS_LEDOR"), nullable = false)
	private LeiturasUsuario ledor;

	@ManyToOne
	@JoinColumn(name = "ID_ASSUNTO", foreignKey = @ForeignKey(name = "FK_LEITURAS_ASSUNTO"), nullable = false)
	private LeiturasAssunto assunto;

	@ManyToOne
	@JoinColumn(name = "ID_TEMPO", foreignKey = @ForeignKey(name = "FK_LEITURAS_TEMPO"), nullable = false)
	private LeiturasTempo tempo;

	@ManyToOne
	@JoinColumn(name = "ID_IDIOMA", foreignKey = @ForeignKey(name = "FK_LEITURAS_IDIOMA"), nullable = false)
	private LeiturasIdioma idioma;

}
