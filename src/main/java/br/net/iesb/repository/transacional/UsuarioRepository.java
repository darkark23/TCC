package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Telefone;
import br.net.iesb.entity.transacional.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByLoginLikeAndSenhaLike (String login,String senha);

    Usuario findByLoginLike (String Login);

    Optional<Usuario> findByLogin (String Login);

    Optional<Usuario> findByIdAndControleAtivo (Long id,Boolean ativo);

    List<Usuario> findAllByControleAtivo (Boolean ativo);

}
