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
@Table(name = "AUTOMOVEL")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Automovel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTOMOVEL_SEQUENCE")
	@SequenceGenerator(name = "AUTOMOVEL_SEQUENCE", sequenceName = "AUTOMOVEL_SEQUENCE", initialValue = 10)
	@Column(name = "ID", nullable = false)
	private Integer id;

	@Column(name = "PLACA", nullable = false)
	private String placa;

	@ManyToOne
	@JoinColumn(name = "ID_MODELO", foreignKey = @ForeignKey(name = "FK_AUTOMOVEL_MODELO"), nullable = false)
	private Modelo modelo;

	@ManyToOne
	@JoinColumn(name = "ID_TRACAO", foreignKey = @ForeignKey(name = "FK_AUTOMOVEL_TRACAO"), nullable = false)
	private Tracao tracao;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA", foreignKey = @ForeignKey(name = "FK_AUTOMOVEL_CATEGORIA"), nullable = false)
	private Categoria categoria;

	@ManyToOne
	@JoinColumn(name = "ID_FABRICANTE", foreignKey = @ForeignKey(name = "FK_AUTOMOVEL_FABRICANTE"), nullable = false)
	private Fabricante fabricante;

}
