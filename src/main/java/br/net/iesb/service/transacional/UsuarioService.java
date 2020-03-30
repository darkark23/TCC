package br.net.iesb.service.transacional;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.repository.transacional.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public UsuarioInformationDTO verificaLogin(UsuarioConfirmacaoDTO usuarioConfirmacao){
        Usuario usuario = usuarioRepository.findByLoginLikeAndSenhaLike(usuarioConfirmacao.getUsuario(),usuarioConfirmacao.getSenha());
        if(usuario == null){
            return new UsuarioInformationDTO();
        } else {
            return new UsuarioInformationDTO(usuario);
        }
    };

    public List<UsuarioDTOResponse> listarUsuariosAtivos(){
        List<Usuario> listaUsuario = usuarioRepository.findAllByControleAtivo(true);
        List<UsuarioDTOResponse> listaUsuarioDTOResponses = new ArrayList<>();
        for (Usuario usuario: listaUsuario) {
            listaUsuarioDTOResponses.add(new UsuarioDTOResponse(usuario));
        }
        return listaUsuarioDTOResponses;
    };

    public Integer desativarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.getControle().setAtivo(false);
        usuarioRepository.saveAndFlush(usuario);
        return 0;
    }

    public Integer aprovarUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.getControle().setAprovado(1);
        usuarioRepository.saveAndFlush(usuario);
        return 0;
    }

    public Integer rejeitarUsuario(UsuarioRequestDTO usuarioDTORequest){
        Usuario usuario = usuarioRepository.findById(usuarioDTORequest.getId()).get();
        usuario.getControle().setAprovado(2);
        usuario.getControle().setDescricaoReprovado(usuarioDTORequest.getMotivo());
        usuarioRepository.saveAndFlush(usuario);
        return 0;
    }

    public UsuarioCadastroResponseDTO getCadastroUsuario(Long id){
        Usuario usuario = usuarioRepository.findByIdAndControleAtivo(id,true).get();
        UsuarioCadastroResponseDTO usuarioCadastroResponseDTO = new UsuarioCadastroResponseDTO(usuario);
        return usuarioCadastroResponseDTO;
    }

}
