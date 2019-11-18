package br.net.iesb.repository.bi;

import br.net.iesb.entity.bi.LeiturasAssunto;
import br.net.iesb.entity.bi.LeiturasUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeiturasUsuarioRepository extends JpaRepository<LeiturasUsuario,Long> {

}
