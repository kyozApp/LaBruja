package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Fuente;

public interface FuenteService 
{
	public List<Fuente> listarFuente();
	public Fuente guardarFuente(Fuente fuente);
	public Fuente actualizarFuente(Fuente fuente);
	public Fuente obtenerIdFuente(Integer fuenteId);
	public void eliminarFuente(Integer fuenteId);
}
