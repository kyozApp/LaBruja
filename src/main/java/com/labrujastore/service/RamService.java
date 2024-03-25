package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Ram;

public interface RamService 
{
	public List<Ram> listarRam();
	public Ram guardarRam(Ram ram);
	public Ram actualizarRam(Ram ram);
	public Ram obtenerIdRam(Integer ramId);
	public void eliminarRam(Integer ramId);
}
