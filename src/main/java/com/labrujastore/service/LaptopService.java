package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Laptop;

public interface LaptopService 
{
	public List<Laptop> listarLaptop();
	public Laptop guardarLaptop(Laptop laptop);
	public Laptop actualizarLaptop(Laptop laptop);
	public Laptop obtenerIdLaptop(Integer laptopId);
	public void eliminarLaptop(Integer laptopId);
	public List<Laptop> obtenerLaptopsPorCategoria(Integer laptopId);
}
