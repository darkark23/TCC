package br.net.iesb.controller;

import br.net.iesb.dto.UsuarioConfirmacaoDTO;
import br.net.iesb.service.transacional.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private UsuarioService usuarioService;

	@ResponseBody
	@GetMapping(path = "/verificarUsuario")
	public Boolean getListaAutomovelView(UsuarioConfirmacaoDTO usuarioConfirmacaoDTO) {
		return usuarioService.verificaLogin(usuarioConfirmacaoDTO);
	}

}
