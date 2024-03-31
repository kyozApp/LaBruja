package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Subcategoria;
import com.labrujastore.repository.SubCategoriaRepository;


@Service
public class SubCategoriaServiceImpl implements SubCategoriaService
{
    @Autowired
    private SubCategoriaRepository subCategoriaRepository;
    
    @Override
	public List<Subcategoria> listarSubCategoria() {
		return subCategoriaRepository.findAll();
	}

    @SuppressWarnings("null")
	@Override
	public Subcategoria guardarSubCategoria(Subcategoria subcategoria) {
		return subCategoriaRepository.save(subcategoria);
	}

    @SuppressWarnings("null")
	@Override
	public Subcategoria actualizarSubCategoria(Subcategoria subcategoria) {
		return subCategoriaRepository.save(subcategoria);
	}

	@SuppressWarnings("null")
	@Override
	public Subcategoria obtenerIdSubCategoria(Integer subcategoriaId) {
		return subCategoriaRepository.findById(subcategoriaId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarSubCategoria(Integer subcategoriaId) {
		subCategoriaRepository.deleteById(subcategoriaId);
	}
}
