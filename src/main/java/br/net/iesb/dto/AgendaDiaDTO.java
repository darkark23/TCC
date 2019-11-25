package br.net.iesb.dto;


import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.util.DataUtil;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
public class AgendaDiaDTO {

    String data;
    List<AulaDTO> listaAulas  = new ArrayList<>();

    public AgendaDiaDTO(List<Aula>  listaAula, Date dataDia){

        this.data = DataUtil.DataAulaDiaCabecalho(dataDia);
        listaAula.forEach(x->this.listaAulas.add(new AulaDTO(x)));

    }

    @Getter
    public class AulaDTO{

        String descricao;
        String id;

        public AulaDTO(Aula aula){

            this.id = aula.getId().toString();
            this.descricao = DataUtil.DataHoraAula(aula.getDataHorario(),aula.getNome());

        }

    }
}
