package br.net.iesb.service.transacional;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.repository.transacional.AudioLivroRepository;
import br.net.iesb.repository.transacional.AulaRepository;
import br.net.iesb.repository.transacional.UsuarioRepository;
import br.net.iesb.util.DataUtil;
import org.assertj.core.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AulaService {

    @Autowired
    AulaRepository aulaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public AgendaDiaDTO getAgendaDia(Date data){
        return new AgendaDiaDTO(aulaRepository.findByDataHorarioBetween(DataUtil.dataInicio(data),DataUtil.dataFinal(data)),data);
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

}
