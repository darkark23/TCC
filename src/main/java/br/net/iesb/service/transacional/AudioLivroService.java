package br.net.iesb.service.transacional;

import br.net.iesb.dto.AudioLivroDetalheDTO;
import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.repository.transacional.AudioLivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AudioLivroService {

    @Autowired
    AudioLivroRepository audioLivroRepository;

    public List<AudioLivroSelecaoDTO> findByLivroReferencia_TituloIsLikeOrTituloLike(String termo){
        List<AudioLivro> listaAudioLivro = audioLivroRepository.findByLivroReferencia_TituloContainingIgnoreCaseOrTituloContainingIgnoreCase(termo,termo);
        List<AudioLivroSelecaoDTO> listaAudioLivroSelecao = new ArrayList<>();
        listaAudioLivro.forEach(x->listaAudioLivroSelecao.add(new AudioLivroSelecaoDTO(x)));
        return listaAudioLivroSelecao;
    }

    public List<AudioLivroSelecaoDTO> findAll(){
        List<AudioLivro> listaAudioLivro = audioLivroRepository.findAll();
        List<AudioLivroSelecaoDTO> listaAudioLivroSelecao = new ArrayList<>();
        listaAudioLivro.forEach(x->listaAudioLivroSelecao.add(new AudioLivroSelecaoDTO(x)));
        return listaAudioLivroSelecao;
    }

    public AudioLivroDetalheDTO findById(String id){
        return new AudioLivroDetalheDTO(audioLivroRepository.findById(Long.parseLong(id)).get());
    }

}
