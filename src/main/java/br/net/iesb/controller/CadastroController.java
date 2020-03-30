package br.net.iesb.controller;

import br.net.iesb.dto.*;
import br.net.iesb.repository.transacional.CidadeRepository;
import br.net.iesb.repository.transacional.EstadoRepository;
import br.net.iesb.repository.transacional.PerfilRepository;
import br.net.iesb.service.transacional.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    CidadeService cidadeService;

    @Autowired
    EstadoService estadoService;

    @Autowired
    PerfilService perfilService;

    @Autowired
    UsuarioService usuarioService;

    @ResponseBody
    @GetMapping(path = "/getListasCadastro")
    public ListasCadastroResponseDTO getListasCadastro() {

        List<CidadeResponseDTO> listaCidadeResponseDTOS = cidadeService.getListaCidadeResponseDTO();
        List<EstadoResponseDTO> listaEstadoResponseDTOS = estadoService.getListaEstadoResponseDTO();
        List<PerfilResponseDTO> listaPerfilResponseDTOS = perfilService.getListaPerfilResponseDTO();

        return new ListasCadastroResponseDTO(listaEstadoResponseDTOS,listaCidadeResponseDTOS,listaPerfilResponseDTOS);
    }

    @ResponseBody
    @GetMapping(path = "/getCadastroUsuario/{idUsuario}")
    public UsuarioCadastroResponseDTO getCadastroUsuario(@PathVariable ("idUsuario") Long idUsuario) {
        return usuarioService.getCadastroUsuario(idUsuario);
    }

}
