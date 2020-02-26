package br.net.iesb.dto;


import br.net.iesb.entity.transacional.AudioLivro;
import lombok.Getter;

@Getter
public class AudioLivroSelecaoDTO {

    private String titulo;
    private String codigo;
    private String id;
    private String aprovado;

    public AudioLivroSelecaoDTO(AudioLivro audioLivro) {
        this.titulo = audioLivro.getTitulo();
        this.codigo = audioLivro.getCodigo();
        this.id = audioLivro.getId().toString();
        this.aprovado = verificarAprovado(audioLivro.getAprovado());
    }

    private String verificarAprovado(Integer aprovado){
        if(aprovado == 0){
            return "Aguardando aprovação";
        }else if (aprovado == 1){
            return "Aprovado";
        }else {
            return "Recusado";
        }
    }
}
