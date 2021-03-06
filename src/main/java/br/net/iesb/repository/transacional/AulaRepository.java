package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    List<Aula> findByDataHorarioBetween(Date dataInicio, Date dataFim);

    List<Aula> findByDataHorarioBetweenAndControleAtivo(Date dataInicio, Date dataFim, Boolean ativo);

    List<Aula> findByDataHorarioBetweenAndControleAtivoAndControleSituacaoAprovacaoId(Date dataInicio, Date dataFim, Boolean ativo, Integer integer);

}
