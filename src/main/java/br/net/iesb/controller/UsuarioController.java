package br.net.iesb.controller;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.service.transacional.AudioLivroService;
import br.net.iesb.service.transacional.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @ResponseBody
    @GetMapping(path = "/listarUsuariosAtivos")
    public List<UsuarioDTOResponse> listarUsuariosAtivos() {
        return usuarioService.listarUsuariosAtivos();
    }

    @ResponseBody
    @GetMapping(path = "/desativarUsuario/{idUsuario}")
    public Integer desativarUsuario(@PathVariable ("idUsuario") Long idUsuario) {
        return usuarioService.desativarUsuario(idUsuario);
    }

    @ResponseBody
    @GetMapping(path = "/aprovarUsuario/{idUsuario}")
    public Integer aprovarUsuario(@PathVariable ("idUsuario") Long idUsuario) {
        return usuarioService.aprovarUsuario(idUsuario);
    }

    @ResponseBody
    @PostMapping(path = "/rejeitarUsuario", consumes = "application/json")
    public Integer rejeitarUsuario(@RequestBody UsuarioRequestDTO usuarioDTORequest) {
        return usuarioService.rejeitarUsuario(usuarioDTORequest);
    }

}
