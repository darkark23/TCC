package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Perfil;
import br.net.iesb.entity.transacional.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa,Long> {

}
