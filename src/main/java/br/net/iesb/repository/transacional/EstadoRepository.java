package br.net.iesb.repository.transacional;

import br.net.iesb.entity.transacional.Autor;
import br.net.iesb.entity.transacional.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstadoRepository extends JpaRepository<Estado,Long> {

}
