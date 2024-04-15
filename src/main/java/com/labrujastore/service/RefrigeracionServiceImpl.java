package com.labrujastore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.repository.RefrigeracionRepository;

@Service
public class RefrigeracionServiceImpl implements RefrigeracionService
{
	@Autowired
	private RefrigeracionRepository refrigeracionRepository;

	@Override
	public List<Refrigeracion> listarRefrigeracion() {
		return refrigeracionRepository.findAll();
	}

	@Override
	public Refrigeracion guardarRefrigeracion(Refrigeracion refrigeracion) {
		return refrigeracionRepository.save(refrigeracion);
	}

	@Override
	public Refrigeracion actualizarRefrigeracion(Refrigeracion refrigeracion) {
		return refrigeracionRepository.save(refrigeracion);
	}

	@Override
	public Refrigeracion obtenerIdRefrigeracion(Integer refrigeracionId) {
		return refrigeracionRepository.findById(refrigeracionId).get();
	}

	@Override
	public void eliminarRefrigeracion(Integer refrigeracionId) {
		refrigeracionRepository.deleteById(refrigeracionId);
	}

	@Override
    public List<Refrigeracion> obtenerRefrigeracionesPorCategoria(Integer refrigeracionId) {

		List<Refrigeracion> refrigeraciones = refrigeracionRepository.findAll();
		List<Refrigeracion> refrigeracionesFinal = new ArrayList<>();
		for (Refrigeracion refrigeracion : refrigeraciones) {
			if (refrigeracionId.equals(refrigeracion.getCategoria().getCategoriaId())) {
				refrigeracionesFinal.add(refrigeracion);
			}
		}
	
		return refrigeracionesFinal;
	}
}
