package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.entity.transacional.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AulaRepository extends JpaRepository<Aula,Long> {

}
