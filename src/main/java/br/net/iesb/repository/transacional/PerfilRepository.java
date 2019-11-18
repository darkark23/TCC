package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Noticia;
import br.net.iesb.entity.transacional.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil,Long> {

}
