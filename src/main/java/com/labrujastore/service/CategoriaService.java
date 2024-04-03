package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Categoria;

public interface CategoriaService 
{
	public List<Categoria> listarCategoria();
	public Categoria guardarCategoria(Categoria categoria);
	public Categoria actualizarCategoria(Categoria categoria);
	public Categoria obtenerIdCategoria(Integer categoriaId);
	public void eliminarCategoria(Integer categoriaId);
	public Categoria obtenerCategoriaNombreUrl(String nombreUrl);
}
