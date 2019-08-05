package br.net.iesb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "FABRICANTE")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Fabricante {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FABRICANTE_SEQUENCE")
	@SequenceGenerator(name = "FABRICANTE_SEQUENCE", sequenceName = "FABRICANTE_SEQUENCE", initialValue = 10)
	private Integer id;

	@Column(name = "NOME", nullable = false)
	private String nome;

	@ManyToOne
	@JoinColumn(name = "ID_PAIS", foreignKey = @ForeignKey(name = "FK_FABRICANTE_PAIS"), nullable = false)
	private Pais pais;

}
