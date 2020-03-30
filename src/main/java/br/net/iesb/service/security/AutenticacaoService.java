package br.net.iesb.service.security;

import br.net.iesb.entity.transacional.Usuario;
import br.net.iesb.repository.transacional.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nomeUsuario) throws UsernameNotFoundException {
        Optional<Usuario> usuario =  usuarioRepository.findByLogin(nomeUsuario);
        if (usuario.isPresent()){
            return usuario.get();
        }else {
           throw new UsernameNotFoundException("Dados invalidos!");
        }
    }
}
