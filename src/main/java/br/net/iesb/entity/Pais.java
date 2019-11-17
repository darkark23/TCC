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
@Table(name = "PAIS")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Pais {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAIS_SEQUENCE")
	@SequenceGenerator(name = "PAIS_SEQUENCE", sequenceName = "PAIS_SEQUENCE")
	private Integer id;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

}
