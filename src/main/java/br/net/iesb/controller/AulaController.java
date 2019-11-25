package br.net.iesb.controller;

import br.net.iesb.dto.AgendaDiaDTO;
import br.net.iesb.dto.AudioLivroDetalheDTO;
import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.service.transacional.AudioLivroService;
import br.net.iesb.service.transacional.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    AulaService aulaService;

    @ResponseBody
    @GetMapping(path = "/agendaDia/{data}")
    public AgendaDiaDTO findByData(@PathVariable ("data") Date data) {
        return aulaService.getAgendaDia(data);
    }
}
