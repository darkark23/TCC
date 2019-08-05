package br.net.iesb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.net.iesb.entity.Categoria;
import br.net.iesb.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	public List<Categoria> getListaCategoria() {
		return categoriaRepository.findAll();
	}
	
	public Categoria getCategoriaById(Integer id) {
		return categoriaRepository.getOne(id);
	}

}
