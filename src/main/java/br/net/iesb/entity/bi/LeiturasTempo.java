package br.net.iesb.entity.bi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LEITURAS_TEMPO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeiturasTempo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEITURAS_TEMPO_SEQUENCE")
	@SequenceGenerator(name = "LEITURAS_TEMPO_SEQUENCE", sequenceName = "LEITURAS_TEMPO_SEQUENCE")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "ANO", nullable = false, length = 4)
	private Integer ano;

	@Column(name = "MES", nullable = false, length = 2)
	private Integer mes;

	@Column(name = "DATA", nullable = false)
	private Date data;

}
