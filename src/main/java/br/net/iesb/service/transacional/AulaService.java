package br.net.iesb.service.transacional;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.*;
import br.net.iesb.enumeration.SituacaoAprovacaoEnum;
import br.net.iesb.repository.transacional.AssuntoRepository;
import br.net.iesb.repository.transacional.AulaRepository;
import br.net.iesb.repository.transacional.SituacaoAprovacaoRepository;
import br.net.iesb.repository.transacional.UsuarioRepository;
import br.net.iesb.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AulaService {

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AssuntoRepository assuntoRepository;

    @Autowired
    SituacaoAprovacaoRepository situacaoAprovacaoRepository;

    public AgendaDiaDTO getAgendaDia(Date data){
        return new AgendaDiaDTO(aulaRepository.findByDataHorarioBetweenAndControleAtivoAndControleSituacaoAprovacaoId(DataUtil.dataInicio(data),DataUtil.dataFinal(data),true,SituacaoAprovacaoEnum.APROVADO.getId()),data);
    };

    public AulaDiaEdicaoDTO getAgendaDiaEdicao(AulaDiaEdicaoRequestDTO request){
        List<Aula> listaAulas = aulaRepository.findByDataHorarioBetweenAndControleAtivo(DataUtil.dataInicio(request.getData()),DataUtil.dataFinal(request.getData()),true);
        Long idUsuario = usuarioRepository.findByLoginLike(request.getUsuario()).getId();
        return new AulaDiaEdicaoDTO(listaAulas,request.getData(),idUsuario);
    };


    public Integer cancelarAula(Long id){
        Aula aula = aulaRepository.findById(id).get();
        aula.getControle().setAtivo(false);
        aulaRepository.saveAndFlush(aula);
        return 0;
    };

    public Integer rejeitarAula(AulaRequestDTO aulaRequestDTO){
        Aula aula = aulaRepository.findById(aulaRequestDTO.getId()).get();
        aula.getControle().setSituacaoAprovacao(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.REPROVADO.getId()).get());
        aula.getControle().setDescricaoReprovado(aulaRequestDTO.getMotivo());
        aulaRepository.saveAndFlush(aula);
        return 0;
    };

    public Integer aprovarAula(Long id){
        Aula aula = aulaRepository.findById(id).get();
        aula.getControle().setSituacaoAprovacao(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.APROVADO.getId()).get());
        aulaRepository.saveAndFlush(aula);
        return 0;
    };

    public Integer saveAula(AulaRequestDTO aulaRequestDTO){
        Usuario usuario = usuarioRepository.findByLoginLike(aulaRequestDTO.getLogin());
        Assunto assunto = assuntoRepository.findById(aulaRequestDTO.getAssunto().getId()).get();
        Aula aula;
        if (aulaRequestDTO.getId() != null){
            aula = aulaRepository.findById(aulaRequestDTO.getId()).get();
        }else {
            aula = new Aula();
        }
        aula.setAssunto(assunto);
        if(aula.getLedor() == null){
            aula.setLedor(usuario);
        }
        aula.setControle(new Controle(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.SUBMETIDO.getId()).get()));
        aula.setDataInsercao(new Date());
        aula.setDescricao(aulaRequestDTO.getDescricao());
        aula.setNome(aulaRequestDTO.getTitulo());
        aula.setDataHorario(DataUtil.setHora(aulaRequestDTO.getData(),aulaRequestDTO.getIdHorario()));
        aulaRepository.saveAndFlush(aula);
        return 0;
    };

}
