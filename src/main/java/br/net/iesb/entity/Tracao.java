package br.net.iesb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TRACAO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Tracao {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRACAO_SEQUENCE")
	@SequenceGenerator(name = "TRACAO_SEQUENCE", sequenceName = "TRACAO_SEQUENCE", initialValue = 10)
	private Integer id;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

}
