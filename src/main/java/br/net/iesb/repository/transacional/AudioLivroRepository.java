package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioLivroRepository extends JpaRepository<AudioLivro,Long> {

}
