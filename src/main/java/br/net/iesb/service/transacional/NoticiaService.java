package br.net.iesb.service.transacional;

import br.net.iesb.dto.NoticiaDTO;
import br.net.iesb.entity.transacional.Noticia;
import br.net.iesb.repository.transacional.NoticiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoticiaService {

    @Autowired
    NoticiaRepository noticiaRepository;

    public List<NoticiaDTO> getNoticias(){
        List<Noticia> listaNoticia = noticiaRepository.findAll();
        final List<NoticiaDTO> listaNoticiaDTO = new ArrayList<>();
        listaNoticia.forEach((x)->{
            listaNoticiaDTO.add(new NoticiaDTO(x));
        });
        return listaNoticiaDTO;
    }

}
