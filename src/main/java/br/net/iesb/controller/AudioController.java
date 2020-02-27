package br.net.iesb.controller;

import br.net.iesb.dto.*;
import br.net.iesb.service.transacional.AudioLivroService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @GetMapping(path = "/remove/{id}")
    public Integer remove(@PathVariable ("id") String id) {
        return audioLivroService.remove(id);
    }

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

    @ResponseBody
    @GetMapping(path = "/findAllLedor")
    public List<AudioLivroSelecaoDTO> findAllByLedor(UsuarioConfirmacaoDTO login) {
        return audioLivroService.findAllByLedor(login.getUsuario());
    }

    @ResponseBody
    @GetMapping(path = "/getListaAudioEdicao")
    public ListaAudioEdicaoDTO getListaAudioEdicao() {
        return audioLivroService.getListaAudioEdicao();
    }

    @ResponseBody
    @PostMapping(path = "/salvarAudioLivro", consumes = "application/json")
    public Integer salvarAudioLivro(@RequestBody AudioLivroEdicaoDTO audioLivro) {
        return audioLivroService.salvarAudioLivro(audioLivro);
    }
}
