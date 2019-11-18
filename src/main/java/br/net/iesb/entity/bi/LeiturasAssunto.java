package br.net.iesb.entity.bi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "LEITURAS_ASSUNTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LeiturasAssunto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEITURAS_ASSUNTO_SEQUENCE")
	@SequenceGenerator(name = "LEITURAS_ASSUNTO_SEQUENCE", sequenceName = "LEITURAS_ASSUNTO_SEQUENCE")
	@Column(name = "ID", nullable = false)
	private Long id;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

}
