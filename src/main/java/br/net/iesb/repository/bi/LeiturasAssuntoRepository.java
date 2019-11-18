package br.net.iesb.repository.bi;

import br.net.iesb.entity.bi.Leituras;
import br.net.iesb.entity.bi.LeiturasAssunto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiturasAssuntoRepository extends JpaRepository<LeiturasAssunto,Long> {

}
