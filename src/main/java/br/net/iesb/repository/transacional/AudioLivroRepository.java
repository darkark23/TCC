package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.AudioLivro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AudioLivroRepository extends JpaRepository<AudioLivro, Long> {

    @Override
    List<AudioLivro> findAll();

    List<AudioLivro> findByTituloContainingIgnoreCaseAndControleAtivoAndControleSituacaoAprovacaoId(String titulo, Boolean ativo, Integer aprovado);

    List<AudioLivro> findByLedorIdAndControleAtivo(Long idUsuario, Boolean ativo);

    List<AudioLivro> findByControleAtivo(Boolean ativo);

    Optional<AudioLivro> findById(Long id);

    Page<AudioLivro> findByLivroReferencia_TituloContainingIgnoreCaseOrTituloContainingIgnoreCaseAndControleAtivoAndControleSituacaoAprovacaoId(String livro, String titulo, Boolean ativo, Integer aprovado, Pageable pageRequest);

}
