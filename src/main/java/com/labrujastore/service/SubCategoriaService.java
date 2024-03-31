package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Subcategoria;

public interface SubCategoriaService 
{
    public List<Subcategoria> listarSubCategoria();
	public Subcategoria guardarSubCategoria(Subcategoria subcategoria);
	public Subcategoria actualizarSubCategoria(Subcategoria subcategoria);
	public Subcategoria obtenerIdSubCategoria(Integer subcategoriaId);
	public void eliminarSubCategoria(Integer subcategoriaId);
}
