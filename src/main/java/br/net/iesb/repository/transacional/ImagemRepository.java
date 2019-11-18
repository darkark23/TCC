package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Idioma;
import br.net.iesb.entity.transacional.Imagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem,Long> {

}
