package br.net.iesb.service.transacional;

import br.net.iesb.dto.AgendaDiaDTO;
import br.net.iesb.dto.AudioLivroDetalheDTO;
import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.repository.transacional.AudioLivroRepository;
import br.net.iesb.repository.transacional.AulaRepository;
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

    public AgendaDiaDTO getAgendaDia(Date data){
        return new AgendaDiaDTO(aulaRepository.findByDataHorarioBetween(DataUtil.dataInicio(data),DataUtil.dataFinal(data)),data);
    };


}
