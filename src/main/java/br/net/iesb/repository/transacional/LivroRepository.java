package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Imagem;
import br.net.iesb.entity.transacional.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro,Long> {

}
