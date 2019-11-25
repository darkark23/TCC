package br.net.iesb.controller;

import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.dto.NoticiaDTO;
import br.net.iesb.service.transacional.AudioLivroService;
import br.net.iesb.service.transacional.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/audioLista")
public class AudioBuscaController {

    @Autowired
    AudioLivroService audioLivroService;

    @ResponseBody
    @GetMapping(path = "/findAll")
    public List<AudioLivroSelecaoDTO> getAll() {
        return audioLivroService.findAll();
    }

    @ResponseBody
    @GetMapping(path = "/findAll/{termo}")
    public List<AudioLivroSelecaoDTO> getAllTermo(@PathVariable ("termo") String termo) {
        return audioLivroService.findByLivroReferencia_TituloIsLikeOrTituloLike(termo);
    }
}
