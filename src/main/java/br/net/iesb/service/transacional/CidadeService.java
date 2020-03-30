package br.net.iesb.service.transacional;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.*;
import br.net.iesb.repository.transacional.AssuntoRepository;
import br.net.iesb.repository.transacional.AulaRepository;
import br.net.iesb.repository.transacional.CidadeRepository;
import br.net.iesb.repository.transacional.UsuarioRepository;
import br.net.iesb.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<CidadeResponseDTO> getListaCidadeResponseDTO(){
        List<Cidade> listaCidade = cidadeRepository.findAll();

        List<CidadeResponseDTO> listaCidadeDTO = new ArrayList<>();
        for (Cidade cidade : listaCidade) {
            listaCidadeDTO.add(new CidadeResponseDTO(cidade));
        }
        return listaCidadeDTO;
    }

}
