package br.net.iesb.repository.bi;

import br.net.iesb.entity.bi.Leituras;
import br.net.iesb.entity.transacional.AcessoAudioLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiturasRepository extends JpaRepository<Leituras,Long> {

}
