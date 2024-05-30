package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Placa;

public interface PlacaService 
{
	public List<Placa> listarPlaca();
	public Placa guardarPlaca(Placa placa);
	public Placa actualizarPlaca(Placa placa);
	public Placa obtenerIdPlaca(Integer placaId);
	public void eliminarPlaca(Integer placaId);
    public List<Placa> obtenerPlacasPorCategoria(Integer placaId);
    public List<Placa> obtenerPlacasPorMarca(Integer marcaId);
	List<Placa> obtenerPlacasCompatibles(Integer procesadorId);
}
