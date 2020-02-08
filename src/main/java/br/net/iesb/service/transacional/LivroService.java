package br.net.iesb.service.transacional;

import br.net.iesb.dto.AudioLivroSelecaoDTO;
import br.net.iesb.dto.LivroDTO;
import br.net.iesb.dto.LivroNovoDTO;
import br.net.iesb.entity.transacional.Autor;
import br.net.iesb.entity.transacional.Livro;
import br.net.iesb.repository.transacional.AutorRepository;
import br.net.iesb.repository.transacional.EditoraRepository;
import br.net.iesb.repository.transacional.IdiomaRepository;
import br.net.iesb.repository.transacional.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    IdiomaRepository idiomaRepository;

    @Autowired
    EditoraRepository editoraRepository;

    public List<LivroDTO> findAll(){
        List<LivroDTO> listaLivroDTO = new ArrayList<>();
        livroRepository.findAll().forEach(x->{
            listaLivroDTO.add(new LivroDTO(x));
        });
        return listaLivroDTO;
    }

    public LivroDTO findById(String id){
        Optional<Livro> livro = livroRepository.findById(Long.parseLong(id));
        if(livro.isPresent()){
            return new LivroDTO(livro.get());
        }else {
            return null;
        }
    }

    public Integer save (LivroNovoDTO livroNovoDTO){
        try {
            Livro novoLivro = new Livro();
            List<Autor> listaAutores = new ArrayList<>();
            livroNovoDTO.getAutores().forEach(x->{
                autorRepository.findById(x).ifPresent(y->listaAutores.add(y));
            });
            novoLivro.setAutores(listaAutores);
            idiomaRepository.findById(livroNovoDTO.getIdioma()).ifPresent(x->novoLivro.setIdioma(x));
            editoraRepository.findById(livroNovoDTO.getEditora()).ifPresent(x->novoLivro.setEditora(x));
            novoLivro.setSinopse(livroNovoDTO.getSinopse());
            novoLivro.setTitulo(livroNovoDTO.getTitulo());
            novoLivro.setDataInsercao(new Date());
            livroRepository.save(novoLivro);
            return 0;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return 1;
        }
    }

}
