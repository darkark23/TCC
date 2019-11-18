package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.entity.transacional.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

}
