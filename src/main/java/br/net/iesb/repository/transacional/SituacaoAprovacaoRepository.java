package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.entity.transacional.SituacaoAprovacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SituacaoAprovacaoRepository extends JpaRepository<SituacaoAprovacao, Integer> {

}
