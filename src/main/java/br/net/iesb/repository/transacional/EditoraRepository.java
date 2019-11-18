package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Competencia;
import br.net.iesb.entity.transacional.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora,Long> {

}
