package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.entity.transacional.ChaveCadastro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ChaveCadastroRepository extends JpaRepository<ChaveCadastro,Long> {

    List<ChaveCadastro> findByChaveAndDataInsercaoAfter (Integer chave, Date dataInsercao);

    List<ChaveCadastro> findByChaveOrderByIdDesc (Integer chave);
}
