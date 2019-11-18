package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Cidade;
import br.net.iesb.entity.transacional.Competencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetenciaRepository extends JpaRepository<Competencia,Long> {

}
