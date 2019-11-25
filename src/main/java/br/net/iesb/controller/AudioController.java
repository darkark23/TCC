package br.net.iesb.controller;

import br.net.iesb.dto.AudioLivroDetalheDTO;
import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.service.transacional.AudioLivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/audio")
public class AudioController {

    @Autowired
    AudioLivroService audioLivroService;

    @ResponseBody
    @GetMapping(path = "/findById/{id}")
    public AudioLivroDetalheDTO findById(@PathVariable ("id") String id) {
        return audioLivroService.findById(id);
    }
}
