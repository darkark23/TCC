package br.net.iesb.controller;

import br.net.iesb.dto.UsuarioConfirmacaoDTO;
import br.net.iesb.dto.UsuarioInformationDTO;
import br.net.iesb.dto.UsuarioLoginDTO;
import br.net.iesb.service.transacional.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Scope("session")
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioInformationDTO usuarioInformationDTO;

    @ResponseBody
    @GetMapping(path = "/verificarUsuario")
    public UsuarioLoginDTO verificarUsuario(UsuarioConfirmacaoDTO usuarioConfirmacaoDTO) {
        if (usuarioInformationDTO.getExistente()) {
            return new UsuarioLoginDTO(usuarioInformationDTO);
        } else {
			UsuarioLoginDTO usuarioLoginDTO = usuarioService.verificaLogin(usuarioConfirmacaoDTO);
			if (usuarioLoginDTO.getExistente()) {
				usuarioInformationDTO = new UsuarioInformationDTO(usuarioLoginDTO);
				return usuarioLoginDTO;
			} else {
				return new UsuarioLoginDTO();
			}
        }
    }

}
