package br.net.iesb.controller;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.Aula;
import br.net.iesb.service.transacional.AssuntoService;
import br.net.iesb.service.transacional.AudioLivroService;
import br.net.iesb.service.transacional.AulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/aula")
public class AulaController {

    @Autowired
    AulaService aulaService;

    @Autowired
    AssuntoService assuntoService;

    @ResponseBody
    @GetMapping(path = "/agendaDia/{data}")
    public AgendaDiaDTO findByData(@PathVariable ("data") Date data) {
        return aulaService.getAgendaDia(data);
    }

    @ResponseBody
    @PostMapping(path = "/agendaDiaEdicao", consumes = "application/json")
    public AulaDiaEdicaoDTO agendaDiaEdicao(@RequestBody AulaDiaEdicaoRequestDTO aulaDiaEdicaoRequestDTO) {
        return aulaService.getAgendaDiaEdicao(aulaDiaEdicaoRequestDTO);
    }

    @ResponseBody
    @PostMapping(path = "/cancelarAula", consumes = "application/json")
    public Integer cancelarAula(@RequestBody Long id) {
        return aulaService.cancelarAula(id);
    }

    @ResponseBody
    @PostMapping(path = "/aprovarAula", consumes = "application/json")
    public Integer aprovarAula(@RequestBody Long id) {
        return aulaService.aprovarAula(id);
    }

    @ResponseBody
    @GetMapping(path = "/getListaAssunto")
    public List<AssuntoEdicaoDTO> getListaAssunto() {
        return assuntoService.getListaAssuntoEdicaoDTO();
    }

    @ResponseBody
    @PostMapping(path = "/saveAula", consumes = "application/json")
    public Integer saveAula(@RequestBody AulaRequestDTO aulaRequestDTO) {
        return aulaService.saveAula(aulaRequestDTO);
    }

    @ResponseBody
    @PostMapping(path = "/rejeitarAula", consumes = "application/json")
    public Integer rejeitarAula(@RequestBody AulaRequestDTO aulaRequestDTO) {
        return aulaService.rejeitarAula(aulaRequestDTO);
    }
}
