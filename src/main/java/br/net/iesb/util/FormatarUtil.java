package br.net.iesb.util;

public final class FormatarUtil {

    public static String getAprovacao(Integer aprovado){
            if(aprovado == 0){
                return "Aguardando aprovação";
            }else if (aprovado == 1){
                return "Aprovado";
            }else {
                return "Recusado";
            }
    }
}
