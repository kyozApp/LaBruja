package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Procesador;

public interface ProcesadorService 
{
	public List<Procesador> listarProcesador();
	public Procesador guardarProcesador(Procesador procesador);
	public Procesador actualizarProcesador(Procesador procesador);
	public Procesador obtenerIdProcesador(Integer procesadorId);
	public void eliminarProcesador(Integer procesadorId);
}
