package br.net.iesb.enumeration;

import lombok.Getter;

@Getter
public enum SituacaoControleEnum {

    PENDENETE(0,"Pendente"),
    APROVADO(1,"Aprovado"),
    REPROVADO(2,"Reprovado");


    private Integer codigo;
    private String nome;

    SituacaoControleEnum(Integer codigo, String nome){
        this.codigo = codigo;
        this.nome = nome;
    }

    public static SituacaoControleEnum getById (Integer id){
        if(id == PENDENETE.codigo){
            return PENDENETE;
        }else if(id == APROVADO.codigo){
            return APROVADO;
        }else if (id == REPROVADO.codigo){
            return REPROVADO;
        }else {
            return null;
        }
    };
}
