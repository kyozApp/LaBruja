package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Accesorio;

public interface AccesorioService 
{
	public List<Accesorio> listarAccesorio();
	public Accesorio guardarAccesorio(Accesorio accesorio);
	public Accesorio actualizarAccesorio(Accesorio accesorio);
	public Accesorio obtenerIdAccesorio(Integer accesorioId);
	public void eliminarAccesorio(Integer accesorioId);
	public List<Accesorio> obtenerAccesoriosPorCategoria(Integer idCategoria);
}
