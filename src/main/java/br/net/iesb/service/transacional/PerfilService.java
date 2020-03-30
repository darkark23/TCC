package br.net.iesb.service.transacional;

import br.net.iesb.dto.EstadoResponseDTO;
import br.net.iesb.dto.PerfilResponseDTO;
import br.net.iesb.entity.transacional.Estado;
import br.net.iesb.entity.transacional.Perfil;
import br.net.iesb.repository.transacional.CidadeRepository;
import br.net.iesb.repository.transacional.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<PerfilResponseDTO> getListaPerfilResponseDTO(){
        List<Perfil> listaPerfil = perfilRepository.findAll();
        List<PerfilResponseDTO> listaPerfilDTO = new ArrayList<>();
        for (Perfil perfil : listaPerfil) {
            listaPerfilDTO.add(new PerfilResponseDTO(perfil));
        }
        return listaPerfilDTO;
    }

}
