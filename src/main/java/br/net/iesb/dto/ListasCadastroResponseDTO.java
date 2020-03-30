package br.net.iesb.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListasCadastroResponseDTO {

    private List<EstadoResponseDTO> listaEstado;
    private List<CidadeResponseDTO> listaCidade;
    private List<PerfilResponseDTO> listaPerfil;

}
