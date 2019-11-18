package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Pessoa;
import br.net.iesb.entity.transacional.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone,Long> {

}
