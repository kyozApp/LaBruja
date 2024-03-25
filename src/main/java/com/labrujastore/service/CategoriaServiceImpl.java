package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Categoria;
import com.labrujastore.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService
{
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public List<Categoria> listarCategoria() {
		return categoriaRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Categoria guardarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@SuppressWarnings("null")
	@Override
	public Categoria actualizarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@SuppressWarnings("null")
	@Override
	public Categoria obtenerIdCategoria(Integer categoriaId) {
		return categoriaRepository.findById(categoriaId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarCategoria(Integer categoriaId) {
		categoriaRepository.deleteById(categoriaId);
	}

}
