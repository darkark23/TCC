package br.net.iesb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.iesb.entity.Modelo;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Integer>{
	
	List<Modelo> findAll();
	
	Optional<Modelo> findByDescricao(String descricao);

}
