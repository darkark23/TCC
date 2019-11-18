package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.AcessoAudioLivro;
import br.net.iesb.entity.transacional.AcessoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoLogRepository extends JpaRepository<AcessoLog,Long> {

}
