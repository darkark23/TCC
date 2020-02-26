package br.net.iesb.service.transacional;

import br.net.iesb.dto.AudioLivroDetalheDTO;
import br.net.iesb.dto.AudioLivroEdicaoDTO;
import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.dto.ListaAudioEdicaoDTO;
import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.entity.transacional.Livro;
import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.repository.transacional.AssuntoRepository;
import br.net.iesb.repository.transacional.AudioLivroRepository;
import br.net.iesb.repository.transacional.LivroRepository;
import br.net.iesb.repository.transacional.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class AudioLivroService {

    @Autowired
    AudioLivroRepository audioLivroRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AssuntoRepository assuntoRepository;

    @Autowired
    LivroRepository livroRepository;

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

    public AudioLivroEdicaoDTO findByIdEdicao(String id){
        return new AudioLivroEdicaoDTO(audioLivroRepository.findById(Long.parseLong(id)).get());
    }

    public Integer remove(String id){
        try{
            AudioLivro audioLivro = audioLivroRepository.findById(Long.parseLong(id)).get();
            audioLivro.setAtivo(false);
            audioLivroRepository.save(audioLivro);
            audioLivroRepository.flush();
            return 1;
        }catch (Exception e) {
            Logger.getGlobal().fine(e.getMessage());
            return 0;
        }
    }

    public List<AudioLivroSelecaoDTO> findAllByLedor(String loginLedor){
        Usuario usuairoLedor = usuarioRepository.findByLoginLike(loginLedor);
        List<AudioLivro> listaAudioLivro = audioLivroRepository.findByLedorIdAndAtivo(usuairoLedor.getId(),true);
        List<AudioLivroSelecaoDTO> listaAudioLivroSelecao = new ArrayList<>();
        listaAudioLivro.forEach(x->listaAudioLivroSelecao.add(new AudioLivroSelecaoDTO(x)));
        return listaAudioLivroSelecao;
    }

    public ListaAudioEdicaoDTO getListaAudioEdicao(){
        List<Livro> listaLivro = livroRepository.findAll();
        List<Assunto> listaAssunto = assuntoRepository.findAll();
        return new ListaAudioEdicaoDTO(listaAssunto,listaLivro);
    }

}
