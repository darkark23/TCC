package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.AcessoAudioLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcessoAudioLivroRepository extends JpaRepository<AcessoAudioLivro,Long> {

}
