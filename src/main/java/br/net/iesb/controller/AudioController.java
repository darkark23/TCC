package br.net.iesb.controller;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.service.transacional.AudioLivroService;
import br.net.iesb.util.PageableImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @GetMapping(path = "/findByIdEdicao/{id}")
    public AudioLivroEdicaoDTO findByIdEdicao(@PathVariable ("id") String id) {
        return audioLivroService.findByIdEdicao(id);
    }

    @ResponseBody
    @GetMapping(path = "/findAll")
    public List<AudioLivroSelecaoDTO> getAll() {
        return audioLivroService.findAll();
    }

    @ResponseBody
    @GetMapping(path = "/findAll/{termo}")
    public List<AudioLivroSelecaoDTO> getAllTermo(@PathVariable ("termo") String termo) {
        return audioLivroService.findByAudioLivro_TituloContainingIgnoreCaseAndControleAtivoAndControleAprovado(termo);
    }

    @ResponseBody
    @GetMapping(path = "/findAllLedor")
    public List<AudioLivroSelecaoDTO> findAllByLedor(UsuarioConfirmacaoDTO usuarioDto) {
        return audioLivroService.findAllByLedor(usuarioDto);
    }

    @ResponseBody
    @GetMapping(path = "/getListaAudioEdicao")
    public ListaAudioEdicaoDTO getListaAudioEdicao() {
        return audioLivroService.getListaAudioEdicao();
    }

    @ResponseBody
    @PutMapping(path = "/submeter", consumes = "application/json")
    public Integer submeter( @RequestBody AudioLivroDTO audioLivro) {
        return audioLivroService.submeter(audioLivro);
    }

    @ResponseBody
    @PutMapping(path = "/reprovar", consumes = "application/json")
    public Integer reprovar(@RequestBody AudioLivroDTO audioLivro) {
        return audioLivroService.reprovar(audioLivro);
    }

    @ResponseBody
    @PutMapping(path = "/aprovar", consumes = "application/json")
    public Integer aprovar(@RequestBody AudioLivroDTO audioLivro) {
        return audioLivroService.aprovar(audioLivro);
    }

    @ResponseBody
    @PutMapping(path = "/remover", consumes = "application/json")
    public Integer remover(@RequestBody AudioLivroDTO audioLivro) {
        return audioLivroService.remover(audioLivro);
    }

    @ResponseBody
    @PostMapping(path = "/salvarAudioLivro", consumes = "application/json")
    public Integer salvarAudioLivro(@RequestBody AudioLivroEdicaoDTO audioLivro) {
        return audioLivroService.salvarAudioLivro(audioLivro);
    }
}
