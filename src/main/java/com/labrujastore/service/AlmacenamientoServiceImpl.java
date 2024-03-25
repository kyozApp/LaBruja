package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.repository.AlmacenamientoRepository;

@Service
public class AlmacenamientoServiceImpl implements AlmacenamientoService
{
	@Autowired
	private AlmacenamientoRepository almacenamientoRepository;

	@Override
	public List<Almacenamiento> listarAlmacenamiento() {
		return almacenamientoRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Almacenamiento guardarAlmacenamiento(Almacenamiento almacenamiento) {
		return almacenamientoRepository.save(almacenamiento);
	}

	@SuppressWarnings("null")
	@Override
	public Almacenamiento actualizarAlmacenamiento(Almacenamiento almacenamiento) {
		return almacenamientoRepository.save(almacenamiento);
	}

	@SuppressWarnings("null")
	@Override
	public Almacenamiento obtenerIdAlmacenamiento(Integer almacenamientoId) {
		return almacenamientoRepository.findById(almacenamientoId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarAlmacenamiento(Integer almacenamientoId) {
		almacenamientoRepository.deleteById(almacenamientoId);
	}

}
