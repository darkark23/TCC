package br.net.iesb.repository.transacional;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.net.iesb.entity.transacional.Fabricante;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {

	List<Fabricante> findAll();
	
	Optional<Fabricante> findById(Integer id);

	@Query("Select f from Fabricante f where f.nome LIKE %:nome% ")
	List<Fabricante> findByNome(@Param(value = "nome") String nome);
	
	@Query("Select f from Fabricante f where f.pais.id = :id")
	List<Fabricante> findByPaisId(@Param(value = "id") Integer id);
	
	@Query("Select f from Fabricante f where f.nome LIKE %:nome% and f.pais.id = :id ")
	List<Fabricante> findByNomeAndByPais(@Param(value = "nome") String nome,@Param(value = "id") Integer id);
	

	
}
