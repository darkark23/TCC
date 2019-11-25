package br.net.iesb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DataUtil {

    private final static SimpleDateFormat FORMAT_DIA = new SimpleDateFormat("dd");
    private final static SimpleDateFormat FORMAT_MES = new SimpleDateFormat("MM");
    private final static SimpleDateFormat FORMAT_ANO = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat FORMAT_HORARIO = new SimpleDateFormat("HH:mm");

    public static Date dataInicio(Date data){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        return calendar.getTime();
    };

    public static Date dataFinal(Date data){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.set(Calendar.HOUR_OF_DAY,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        calendar.set(Calendar.MILLISECOND,59);
        return calendar.getTime();
    };

    private static Date dataAdicionarHora(Date data){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(data);
        calendar.add(Calendar.HOUR_OF_DAY,1);
        return calendar.getTime();
    };

    public static String DataAulaDiaCabecalho(Date data){
        return FORMAT_DIA.format(data) + "/" + FORMAT_MES.format(data) + "/" + FORMAT_ANO.format(data);
    };

    public static String DataHoraAula(Date data, String nome){
        return  FORMAT_HORARIO.format(data) + " - " + FORMAT_HORARIO.format(dataAdicionarHora(data)) + " - " + nome;
    };

}
