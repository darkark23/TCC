package br.net.iesb.service.transacional;

import br.net.iesb.dto.AssuntoEdicaoDTO;
import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.ChaveCadastro;
import br.net.iesb.repository.transacional.AssuntoRepository;
import br.net.iesb.repository.transacional.ChaveCadastroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChaveCadastroService {

    @Autowired
    ChaveCadastroRepository chaveCadastroRepository;

    public Integer gerarChaveCadastro (){

        Random random = new Random();
        Boolean isExistente = true;
        Integer novaChave = 0;

        while (isExistente){
            int c = 0;
            novaChave = random.nextInt(90000) + 10000;
            if(chaveCadastroRepository.findByChaveAndDataInsercaoAfter(novaChave,dataLimiteChave()).isEmpty()){
                ChaveCadastro chaveCadastro = new ChaveCadastro(novaChave);
                chaveCadastroRepository.saveAndFlush(chaveCadastro);
                isExistente = false;
            }
            if(c++ > 90000){
                novaChave = 0;
                isExistente = false;
            }
        }
        return novaChave;
    }

    public Integer validarChaveCadastro(Integer chaveCadastro){
        List<ChaveCadastro> listaChave = chaveCadastroRepository.findByChaveAndDataInsercaoAfter(chaveCadastro,dataLimiteChave());
        if(listaChave.isEmpty() || listaChave.get(0).getChave() == 0){
            return 0;
        }else {
            return 1;
        }
    }

    public Integer desabilitarChaveCadastro(Integer chaveCadastro){
        List<ChaveCadastro> listaChave = chaveCadastroRepository.findByChaveOrderByIdDesc(chaveCadastro);
        ChaveCadastro chaveCadastroAlteracao = listaChave.get(0);
        chaveCadastroRepository.delete(chaveCadastroAlteracao);
        return 1;
    }

    public Date dataLimiteChave(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, -10);
        return calendar.getTime();
    }

}
