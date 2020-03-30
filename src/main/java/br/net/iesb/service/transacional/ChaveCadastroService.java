package br.net.iesb.service.transacional;

import br.net.iesb.dto.AssuntoEdicaoDTO;
import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.repository.transacional.AssuntoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChaveCadastroService {

    @Autowired
    AssuntoRepository assuntoRepository;

    public List<AssuntoEdicaoDTO> getListaAssuntoEdicaoDTO(){
        List<Assunto> listaAssunto = assuntoRepository.findAll();
        List<AssuntoEdicaoDTO> listaAssuntoSelecao = new ArrayList<>();
        for (Assunto assunto: listaAssunto) {
            listaAssuntoSelecao.add(new AssuntoEdicaoDTO(assunto));
        }
        return listaAssuntoSelecao;
    };

}
