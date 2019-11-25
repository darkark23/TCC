package br.net.iesb.repository.bi;

import br.net.iesb.entity.bi.LeiturasLeitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiturasLeitorRepository extends JpaRepository<LeiturasLeitor,Long> {

}
