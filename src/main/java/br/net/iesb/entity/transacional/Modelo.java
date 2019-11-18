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
@Table(name = "MODELO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Modelo {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MODELO_SEQUENCE")
	@SequenceGenerator(name = "MODELO_SEQUENCE", sequenceName = "MODELO_SEQUENCE", initialValue = 10)
	private Integer id;
	
	@Column(name="DESCRICAO", nullable = false)
	private String descricao;

}
