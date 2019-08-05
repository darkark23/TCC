package br.net.iesb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.net.iesb.entity.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Integer> {

	Optional<Automovel> findById(Integer id);
	
	Optional<Automovel> findByPlaca(String placa);
	
	List<Automovel> findAll();
	
	@Modifying
	@Query("delete from Automovel a where a.id=:id")
	void deleteAutomovel(@Param("id") Integer id);
}
