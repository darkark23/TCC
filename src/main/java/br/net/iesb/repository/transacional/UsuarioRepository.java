package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Telefone;
import br.net.iesb.entity.transacional.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByLoginLikeAndSenhaLike (String login,String senha);

}
