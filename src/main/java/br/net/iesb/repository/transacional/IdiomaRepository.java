package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Funcionalidade;
import br.net.iesb.entity.transacional.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdiomaRepository extends JpaRepository<Idioma,Long> {

}
