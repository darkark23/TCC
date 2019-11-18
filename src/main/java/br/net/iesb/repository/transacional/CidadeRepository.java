package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Autor;
import br.net.iesb.entity.transacional.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade,Long> {

}
