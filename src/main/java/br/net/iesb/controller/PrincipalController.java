package br.net.iesb.controller;

import br.net.iesb.dto.NoticiaDTO;
import br.net.iesb.entity.transacional.Noticia;
import br.net.iesb.json.AutomovelFiltroJson;
import br.net.iesb.service.transacional.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/principal")
public class PrincipalController {

    @Autowired
    NoticiaService noticiaService;

    @ResponseBody
    @GetMapping(path = "/getNoticias")
    public List<NoticiaDTO> getNoticias() {
        return noticiaService.getNoticias();
    }
}
