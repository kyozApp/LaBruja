package com.labrujastore.service;

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

	@SuppressWarnings("null")
	@Override
	public Refrigeracion guardarRefrigeracion(Refrigeracion refrigeracion) {
		return refrigeracionRepository.save(refrigeracion);
	}

	@SuppressWarnings("null")
	@Override
	public Refrigeracion actualizarRefrigeracion(Refrigeracion refrigeracion) {
		return refrigeracionRepository.save(refrigeracion);
	}

	@SuppressWarnings("null")
	@Override
	public Refrigeracion obtenerIdRefrigeracion(Integer refrigeracionId) {
		return refrigeracionRepository.findById(refrigeracionId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarRefrigeracion(Integer refrigeracionId) {
		refrigeracionRepository.deleteById(refrigeracionId);
	}

}
