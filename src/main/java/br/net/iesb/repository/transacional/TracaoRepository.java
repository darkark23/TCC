package br.net.iesb.repository.transacional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.iesb.entity.transacional.Tracao;

@Repository
public interface TracaoRepository extends JpaRepository<Tracao, Integer> {

	List<Tracao> findAll();
	
}
