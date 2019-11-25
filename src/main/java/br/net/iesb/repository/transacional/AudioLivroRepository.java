package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.AudioLivro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AudioLivroRepository extends JpaRepository<AudioLivro,Long> {

    @Override
    List<AudioLivro> findAll();


    List<AudioLivro> findByLivroReferencia_TituloContainingIgnoreCaseOrTituloContainingIgnoreCase(String livro, String titulo);

    Optional<AudioLivro> findById(Long id);

}
