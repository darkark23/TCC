package br.net.iesb.service.transacional;

import br.net.iesb.dto.*;
import br.net.iesb.entity.transacional.*;
import br.net.iesb.enumeration.SituacaoAprovacaoEnum;
import br.net.iesb.repository.transacional.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    SituacaoAprovacaoRepository situacaoAprovacaoRepository;

    @Autowired
    PerfilRepository perfilRepository;

    @Autowired
    CidadeRepository cidadeRepository;

    @Autowired
    EstadoRepository estadoRepository;

    @Autowired
    PessoaRepository  pessoaRepository;

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
        usuario.getControle().setSituacaoAprovacao(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.APROVADO.getId()).get());
        usuarioRepository.saveAndFlush(usuario);
        return 0;
    }

    public Integer rejeitarUsuario(UsuarioRequestDTO usuarioDTORequest){
        Usuario usuario = usuarioRepository.findById(usuarioDTORequest.getId()).get();
        usuario.getControle().setSituacaoAprovacao(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.REPROVADO.getId()).get());
        usuario.getControle().setDescricaoReprovado(usuarioDTORequest.getMotivo());
        usuarioRepository.saveAndFlush(usuario);
        return 0;
    }

    public UsuarioCadastroResponseDTO getCadastroUsuario(Long id){
        Usuario usuario = usuarioRepository.findByIdAndControleAtivo(id,true).get();
        UsuarioCadastroResponseDTO usuarioCadastroResponseDTO = new UsuarioCadastroResponseDTO(usuario);
        return usuarioCadastroResponseDTO;
    }

    public Integer salvarUsuario(UsuarioCadastroResponseDTO usuarioDTO){

        Controle controle;
        Endereco endereco;
        Pessoa pessoa;
        Usuario usuario;
        Telefone telefone;

        if(usuarioDTO.getId() != null){
            usuario = usuarioRepository.findById(usuarioDTO.getId()).get();
            controle = usuario.getControle();
            endereco = usuario.getPessoa().getEndereco();
            pessoa = usuario.getPessoa();
            telefone = usuario.getPessoa().getTelefone();
        }else {
            controle = new Controle();
            endereco = new Endereco();
            pessoa = new Pessoa();
            usuario = new Usuario();
            telefone = new Telefone();
        }

        telefone.setDataInsercao(new Date());
        telefone.setNumero(usuarioDTO.getPessoa().getTelefone().getNumero());
        telefone.setTipo(usuarioDTO.getPessoa().getTelefone().getTipo());

        controle.setAtivo(true);
        controle.setSituacaoAprovacao(situacaoAprovacaoRepository.findById(SituacaoAprovacaoEnum.SUBMETIDO.getId()).get());

        endereco.setBairro(usuarioDTO.getPessoa().getEndereco().getBairro());
        endereco.setCep(usuarioDTO.getPessoa().getEndereco().getCep());
        endereco.setCidade(cidadeRepository.findById(usuarioDTO.getPessoa().getEndereco().getCidadeId()).get());
        endereco.setComplemento(usuarioDTO.getPessoa().getEndereco().getComplemento());
        endereco.setDataInsercao(new Date());
        endereco.setBairro(usuarioDTO.getPessoa().getEndereco().getBairro());

        pessoa.setTelefone(telefone);
        pessoa.setCpf(usuarioDTO.getPessoa().getCpf());
        pessoa.setDataInsercao(new Date());
        pessoa.setNome(usuarioDTO.getPessoa().getNome());
        pessoa.setRg(usuarioDTO.getPessoa().getRg());
        pessoa.setSexo(usuarioDTO.getPessoa().getSexo());
        pessoa.setDataNascimento(usuarioDTO.getPessoa().getDataNascimento());
        pessoa.setEndereco(endereco);

        usuario.setControle(controle);
        usuario.setDataInsercao(new Date());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setPerfil(perfilRepository.findById(usuarioDTO.getPerfil()).get());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setPessoa(pessoa);

        try {
            usuarioRepository.saveAndFlush(usuario);
            return 1;
        }catch (Exception e){
            return 0;
        }
    }

}
