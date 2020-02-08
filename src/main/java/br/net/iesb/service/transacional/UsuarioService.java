package br.net.iesb.service.transacional;

import br.net.iesb.dto.UsuarioConfirmacaoDTO;
import br.net.iesb.dto.UsuarioLoginDTO;
import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.repository.transacional.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioLoginDTO verificaLogin(UsuarioConfirmacaoDTO usuarioConfirmacao){
        Usuario usuario = usuarioRepository.findByLoginLikeAndSenhaLike(usuarioConfirmacao.getUsuario(),usuarioConfirmacao.getSenha());
        if(usuario == null){
            return new UsuarioLoginDTO();
        } else {
            return new UsuarioLoginDTO(usuario);
        }
    };


}