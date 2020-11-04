package br.net.iesb.enumeration;

import lombok.Getter;

@Getter
public enum SituacaoAprovacaoEnum {

    PENDENETE(1,"Pendente"),
    APROVADO(2,"Aprovado"),
    REPROVADO(3,"Reprovado"),
    SUBMETIDO(4,"Submetido");

    private Integer id;
    private String nome;

    SituacaoAprovacaoEnum(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public static SituacaoAprovacaoEnum getByCodigo(Integer codigo){
        for (SituacaoAprovacaoEnum situacao: SituacaoAprovacaoEnum.values()) {
            if(codigo == situacao.getId()){
                return situacao;
            }
        }
        return null;
    }

}
