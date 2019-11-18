package br.net.iesb.entity.transacional;

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

import java.util.Date;

@Entity
@Table(name = "CATEGORIA")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Categoria {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIA_SEQUENCE")
	@SequenceGenerator(name = "CATEGORIA_SEQUENCE", sequenceName = "CATEGORIA_SEQUENCE")
	private Integer id;

	@Column(name = "DESCRICAO", nullable = false)
	private String descricao;

	@Column(name = "DATA_INSERCAO", nullable = true)
	private Date dataInsercao;

}
