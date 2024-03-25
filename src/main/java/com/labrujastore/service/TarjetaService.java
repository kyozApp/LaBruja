package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Tarjeta;

public interface TarjetaService 
{
	public List<Tarjeta> listarTarjeta();
	public Tarjeta guardarTarjeta(Tarjeta tarjeta);
	public Tarjeta actualizarTarjeta(Tarjeta tarjeta);
	public Tarjeta obtenerIdTarjeta(Integer tarjetaId);
	public void eliminarTarjeta(Integer tarjetaId);
}
