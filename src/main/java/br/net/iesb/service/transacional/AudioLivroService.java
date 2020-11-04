package br.net.iesb.service.transacional;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.*;
import br.net.iesb.enumeration.SituacaoAprovacaoEnum;
import br.net.iesb.repository.transacional.*;
import br.net.iesb.util.PageableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    SituacaoAprovacaoRepository situacaoAprovacaoRepository;

    public List<AudioLivroSelecaoDTO> findByAudioLivro_TituloContainingIgnoreCaseAndControleAtivoAndControleAprovado(String termo){
        List<AudioLivro> listaAudioLivro = audioLivroRepository.findByTituloContainingIgnoreCaseAndControleAtivoAndControleSituacaoAprovacaoId(termo,true,SituacaoAprovacaoEnum.APROVADO.getId());
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

    public List<AudioLivroSelecaoDTO> findAllByLedor(UsuarioConfirmacaoDTO usuarioDto){
        Usuario usuairo = usuarioRepository.findByLoginLike(usuarioDto.getUsuario());
        List<AudioLivro> listaAudioLivro;
        if (usuairo.getPerfil().getId() == 3){
            listaAudioLivro = audioLivroRepository.findByLedorIdAndControleAtivo(usuairo.getId(),true);
        }else {
            listaAudioLivro = audioLivroRepository.findByControleAtivo(true);
        }
        List<AudioLivroSelecaoDTO> listaAudioLivroSelecao = new ArrayList<>();
        listaAudioLivro.forEach(x->listaAudioLivroSelecao.add(new AudioLivroSelecaoDTO(x)));
        return listaAudioLivroSelecao;
    }

    public ListaAudioEdicaoDTO getListaAudioEdicao(){
        List<Livro> listaLivro = livroRepository.findAll();
        List<Assunto> listaAssunto = assuntoRepository.findAll();
        return new ListaAudioEdicaoDTO(listaAssunto,listaLivro);
    }

    public Integer salvarAudioLivro(AudioLivroEdicaoDTO audioLivroDTO){
        AudioLivro audioLivro = audioLivroDTO.getId() != null ? audioLivroRepository.findById(audioLivroDTO.getId()).get() : new AudioLivro();
        audioLivro.setTitulo(audioLivroDTO.getTituloAudioBook());
        audioLivro.setUrl(audioLivroDTO.getUrl());
        audioLivro.setCodigo(audioLivroDTO.getCodigo() != null ? audioLivroDTO.getCodigo() : null);
        if(audioLivroDTO.getUrlTexto() != null){
            audioLivro.setUrlTexto(audioLivroDTO.getUrlTexto());
        }
        audioLivro.setDescricao(audioLivroDTO.getDescricao());
        if(audioLivro.getControle() == null){
            audioLivro.setControle(new Controle(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.PENDENETE.getId()).get()));
        }else {
            audioLivro.getControle().setSituacaoAprovacao(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.PENDENETE.getId()).get());
        }
        if(audioLivro.getLedor() == null){
            audioLivro.setLedor(usuarioRepository.findByLoginLike(audioLivroDTO.getLedor()));
        }
        audioLivro.setLivroReferencia(livroRepository.findById(audioLivroDTO.getLivro().getId()).get());
        List<Long> listaIdAssunto = new ArrayList<>();
        for (AssuntoEdicaoDTO assunto : audioLivroDTO.getListaAssunto()) {
            listaIdAssunto.add(assunto.getId());
        }
        audioLivro.setAssuntos(assuntoRepository.findAllById(listaIdAssunto));
        audioLivro.setDataInsercao(new Date());
        audioLivroRepository.saveAndFlush(audioLivro);
        return 0;
    }

    public Integer reprovar(AudioLivroDTO dto){
        return processarAudioLivro(dto, SituacaoAprovacaoEnum.REPROVADO);
    }

    public Integer submeter(AudioLivroDTO dto){
        return processarAudioLivro(dto, SituacaoAprovacaoEnum.SUBMETIDO);
    }

    public Integer aprovar(AudioLivroDTO dto){
        return processarAudioLivro(dto, SituacaoAprovacaoEnum.APROVADO);
    }

    private Integer processarAudioLivro(AudioLivroDTO dto, SituacaoAprovacaoEnum situacaoEnum){
        try{
            AudioLivro audioLivro = audioLivroRepository.findById(dto.getId()).get();
            audioLivro.getControle().setSituacaoAprovacao(situacaoAprovacaoRepository.findById(situacaoEnum.getId()).get());
            audioLivro.getControle().setDescricaoReprovado(dto.getControle().getDescricaoReprovado());
            audioLivroRepository.save(audioLivro);
            audioLivroRepository.flush();
            return 1;
        }catch (Exception e) {
            Logger.getGlobal().fine(e.getMessage());
            return 0;
        }
    }

    public Integer remover(AudioLivroDTO dto){
        try{
            AudioLivro audioLivro = audioLivroRepository.findById(dto.getId()).get();
            audioLivro.getControle().setAtivo(false);
            audioLivroRepository.save(audioLivro);
            audioLivroRepository.flush();
            return 1;
        }catch (Exception e) {
            Logger.getGlobal().fine(e.getMessage());
            return 0;
        }
    }

}
