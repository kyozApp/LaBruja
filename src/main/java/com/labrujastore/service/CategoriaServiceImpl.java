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

	@Override
	public Categoria guardarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria actualizarCategoria(Categoria categoria) {
		return categoriaRepository.save(categoria);
	}

	@Override
	public Categoria obtenerIdCategoria(Integer categoriaId) {
		return categoriaRepository.findById(categoriaId).get();
	}

	@Override
	public void eliminarCategoria(Integer categoriaId) {
		categoriaRepository.deleteById(categoriaId);
	}

	@Override
	public Categoria obtenerCategoriaNombreUrl(String nombreUrl) {
		if (nombreUrl == null) {
			return null; // Manejar caso de nombreUrl nulo
		}
		List<Categoria> categorias = categoriaRepository.findAll();
		for (Categoria categoria : categorias) {
			if (nombreUrl.equals(categoria.getNombreUrl())) {
				return categoria; // Retorna la categoría encontrada
			}
		}
		return null; // Si no se encuentra la categoría, retorna nulo
	}
}
