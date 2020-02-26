package br.net.iesb.dto;

import br.net.iesb.entity.transacional.Assunto;
import br.net.iesb.entity.transacional.Livro;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ListaAudioEdicaoDTO {

    private List<AssuntoEdicaoDTO> listaAssuntoSelecao;
    private List<LivroEdicaoDTO> listaLivroSelecao;

    public ListaAudioEdicaoDTO(List<Assunto> listaAssunto, List<Livro> listaLivro){
        listaAssuntoSelecao = new ArrayList<>();
        listaLivroSelecao = new ArrayList<>();

        for (Assunto assunto: listaAssunto) {
            listaAssuntoSelecao.add(new AssuntoEdicaoDTO(assunto));
        }

        for (Livro livro: listaLivro) {
            listaLivroSelecao.add(new LivroEdicaoDTO(livro));
        }
    }

}
