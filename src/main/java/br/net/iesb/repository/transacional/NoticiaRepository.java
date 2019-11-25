package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Livro;
import br.net.iesb.entity.transacional.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticiaRepository extends JpaRepository<Noticia,Long> {

    List<Noticia> findAll();

}
