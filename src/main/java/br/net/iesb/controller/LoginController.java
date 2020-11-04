package br.net.iesb.controller;

import br.net.iesb.dto.UsuarioConfirmacaoDTO;
import br.net.iesb.dto.UsuarioInformationDTO;
import br.net.iesb.dto.UsuarioLoginDTO;
import br.net.iesb.enumeration.SituacaoAprovacaoEnum;
import br.net.iesb.service.transacional.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
            UsuarioInformationDTO usuarioInformationDTOReturn = usuarioService.verificaLogin(usuarioConfirmacaoDTO);
			if (usuarioInformationDTOReturn.getExistente() && usuarioInformationDTOReturn.getIdSituacaoAprovacao() == SituacaoAprovacaoEnum.APROVADO.getId()) {
			    usuarioInformationDTO = usuarioInformationDTOReturn;
				return new UsuarioLoginDTO(usuarioInformationDTO);
			} else if (usuarioInformationDTO.getExistente() && usuarioInformationDTO.getIdSituacaoAprovacao() != SituacaoAprovacaoEnum.APROVADO.getId()) {
                return new UsuarioLoginDTO(usuarioInformationDTOReturn);
			}else {
                return new UsuarioLoginDTO();
            }
        }
    }

    public String retornarUsuarioLogin() {
        if (usuarioInformationDTO.getExistente()) {
            return usuarioInformationDTO.getLogin();
        } else {
            return null;
        }
    }

    @ResponseBody
    @GetMapping(path = "/logOff")
    public void logOff() {
        usuarioInformationDTO = new UsuarioInformationDTO();
    }

}
