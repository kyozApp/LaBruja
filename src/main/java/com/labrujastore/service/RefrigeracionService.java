package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Refrigeracion;

public interface RefrigeracionService 
{
	public List<Refrigeracion> listarRefrigeracion();
	public Refrigeracion guardarRefrigeracion(Refrigeracion refrigeracion);
	public Refrigeracion actualizarRefrigeracion(Refrigeracion refrigeracion);
	public Refrigeracion obtenerIdRefrigeracion(Integer refrigeracionId);
	public void eliminarRefrigeracion(Integer refrigeracionId);
}
