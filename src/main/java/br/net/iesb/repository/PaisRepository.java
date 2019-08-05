package br.net.iesb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.net.iesb.entity.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
	
	List<Pais> findAll();

}
