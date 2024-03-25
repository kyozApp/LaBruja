package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Almacenamiento;

public interface AlmacenamientoService 
{
	public List<Almacenamiento> listarAlmacenamiento();
	public Almacenamiento guardarAlmacenamiento(Almacenamiento almacenamiento);
	public Almacenamiento actualizarAlmacenamiento(Almacenamiento almacenamiento);
	public Almacenamiento obtenerIdAlmacenamiento(Integer almacenamientoId);
	public void eliminarAlmacenamiento(Integer almacenamientoId);
}
