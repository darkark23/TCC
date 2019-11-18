package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Livro;
import br.net.iesb.entity.transacional.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia,Long> {

}
