package br.net.iesb.controller;

import br.net.iesb.dto.LivroDTO;
import br.net.iesb.dto.LivroNovoDTO;
import br.net.iesb.service.transacional.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/livro")
public class LivroController {

    final LivroService livroService;

    @Autowired
    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @ResponseBody
    @GetMapping(path = "/findById/{id}")
    public LivroDTO findById(@PathVariable ("id") String id) {
        return livroService.findById(id);
    }

    @ResponseBody
    @GetMapping(path = "/findAll")
    public List<LivroDTO> findAll() {
        return livroService.findAll();
    }

    @ResponseBody
    @GetMapping(path = "/save")
    public Integer save(LivroNovoDTO livroNovoDTO) {
        return livroService.save(livroNovoDTO);
    }

}
