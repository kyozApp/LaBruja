package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Casse;

public interface CasseService 
{
	public List<Casse> listarCasse();
	public Casse guardarCasse(Casse casse);
	public Casse actualizarCasse(Casse casse);
	public Casse obtenerIdCasse(Integer casseId);
	public void eliminarCasse(Integer casseId);
	public List<Casse> obtenerCassesPorCategoria(Integer casseId);
}
