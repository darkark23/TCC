package br.net.iesb.repository.bi;

import br.net.iesb.entity.bi.LeiturasIdioma;
import br.net.iesb.entity.bi.LeiturasTempo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiturasTempoRepository extends JpaRepository<LeiturasTempo,Long> {

}
