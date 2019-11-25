package br.net.iesb.repository.bi;

import br.net.iesb.entity.bi.LeiturasLedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiturasLedorRepository extends JpaRepository<LeiturasLedor,Long> {

}
