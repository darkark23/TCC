package br.net.iesb.controller;

import br.net.iesb.dto.AudioLivroDetalheDTO;
import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.service.transacional.AudioLivroService;
import br.net.iesb.service.transacional.ChaveCadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/chave")
public class ChaveCadstroController {

    @Autowired
    ChaveCadastroService chaveCadastroService;

    @ResponseBody
    @GetMapping(path = "/gerarChaveCadastro")
    public Integer gerarChaveCadastro() {
        return chaveCadastroService.gerarChaveCadastro();
    }

    @ResponseBody
    @GetMapping(path = "/validarChaveCadastro/{chaveCadastro}")
    public Integer validarChaveCadastro(@PathVariable("chaveCadastro") Integer chaveCadastro) {
        return chaveCadastroService.validarChaveCadastro(chaveCadastro);
    }

    @ResponseBody
    @GetMapping(path = "/desabilitarChaveCadastro/{chaveCadastro}")
    public Integer desabilitarChaveCadastro(@PathVariable("chaveCadastro") Integer chaveCadastro) {
        return chaveCadastroService.desabilitarChaveCadastro(chaveCadastro);
    }

}
