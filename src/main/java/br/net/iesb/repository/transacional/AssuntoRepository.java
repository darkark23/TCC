package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.AcessoLog;
import br.net.iesb.entity.transacional.Assunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssuntoRepository extends JpaRepository<Assunto,Long> {

}
