package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Editora;
import br.net.iesb.entity.transacional.Funcionalidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionalidadeRepository extends JpaRepository<Funcionalidade,Long> {

}
