package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.util.DataUtil;
import br.net.iesb.util.FormatarUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class AulaDiaEdicaoDTO {

    String data;
    Map<Integer,AulaDiaEdicaoDTO.AulaDTO> listaAulas ;

    public AulaDiaEdicaoDTO(List<Aula> listaAula, Date dataDia, Long idLedor){
        this.data = DataUtil.DataAulaDiaCabecalho(dataDia);
        this.listaAulas = construirAgenda();
        listaAula.forEach(x->{
            this.listaAulas.put(DataUtil.getHora(x.getDataHorario()),new AulaDiaEdicaoDTO.AulaDTO(x,idLedor,DataUtil.getHora(x.getDataHorario())));
        });
    }

    private Map<Integer, AulaDiaEdicaoDTO.AulaDTO> construirAgenda(){
        Map<Integer, AulaDiaEdicaoDTO.AulaDTO> listaAulas = new HashMap<>();
        listaAulas.put(8,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 08:00 às 09:00",8));
        listaAulas.put(9, new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 09:00 às 10:00",9));
        listaAulas.put(10,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 10:00 às 11:00",10));
        listaAulas.put(11,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 11:00 às 12:00",11));
        listaAulas.put(14,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 14:00 às 15:00",14));
        listaAulas.put(15,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 15:00 às 16:00",15));
        listaAulas.put(16,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 16:00 às 17:00",16));
        listaAulas.put(17,new AulaDiaEdicaoDTO.AulaDTO("Horário livre das 17:00 às 18:00",17));
        return listaAulas;
    };

    @Getter
    public class AulaDTO{
        String descricao;
        String nome;
        String descricaoAula;
        String id;
        Integer idAprovado;
        Long idAssunto;
        Integer horarioId;
        Assunto assunto;
        String ledor;
        String login;
        Integer idSituacaoAprovacao;
        String motivoRejeicao;
        boolean aulaLedor ;

        public AulaDTO(String descricao,Integer horarioId){
            this.descricao = descricao;
            this.horarioId = horarioId;
        }

        public AulaDTO(Aula aula, Long idLedor,Integer horarioId){
            this.nome = aula.getNome();
            this.id = aula.getId().toString();
            this.horarioId = horarioId;
            this.descricao = DataUtil.DataHoraAula(aula.getDataHorario(),aula.getNome());
            this.assunto = aula.getAssunto();
            this.ledor =  aula.getLedor().getPessoa().getNome();
            this.login =  aula.getLedor().getLogin();
            this.aulaLedor = aula.getLedor().getId() == idLedor;
            this.idAprovado = aula.getControle().getSituacaoAprovacao().getId();
            if(aula.getControle().getDescricaoReprovado() != null){
                this.motivoRejeicao = aula.getControle().getDescricaoReprovado();
            }
            if (aula.getDescricao() != null){
                this.descricaoAula = aula.getDescricao();
            }
            this.motivoRejeicao = aula.getControle().getDescricaoReprovado();
            this.idSituacaoAprovacao = aula.getControle().getSituacaoAprovacao().getId();
        }

    }

}
