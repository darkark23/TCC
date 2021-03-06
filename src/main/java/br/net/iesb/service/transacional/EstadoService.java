package br.net.iesb.service.transacional;

import br.net.iesb.dto.EstadoResponseDTO;
import br.net.iesb.entity.transacional.Estado;
import br.net.iesb.repository.transacional.CidadeRepository;
import br.net.iesb.repository.transacional.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<EstadoResponseDTO> getListaEstadoResponseDTO(){
        List<Estado> listaEstado = estadoRepository.findAll();
        List<EstadoResponseDTO> listaEstadoDTO = new ArrayList<>();
        for (Estado estado : listaEstado) {
            listaEstadoDTO.add(new EstadoResponseDTO(estado));
        }
        return listaEstadoDTO;
    }

}
