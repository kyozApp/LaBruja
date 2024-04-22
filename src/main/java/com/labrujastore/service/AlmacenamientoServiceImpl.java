package com.labrujastore.service;

import java.util.ArrayList;
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

	@Override
	public Almacenamiento guardarAlmacenamiento(Almacenamiento almacenamiento) {
		return almacenamientoRepository.save(almacenamiento);
	}

	@Override
	public Almacenamiento actualizarAlmacenamiento(Almacenamiento almacenamiento) {
		return almacenamientoRepository.save(almacenamiento);
	}

	@Override
	public Almacenamiento obtenerIdAlmacenamiento(Integer almacenamientoId) {
		return almacenamientoRepository.findById(almacenamientoId).get();
	}

	@Override
	public void eliminarAlmacenamiento(Integer almacenamientoId) {
		almacenamientoRepository.deleteById(almacenamientoId);
	}

	@Override
    public List<Almacenamiento> obtenerAlmacenamientosPorCategoria(Integer almacenamientoId) {

		List<Almacenamiento> almacenamientos = almacenamientoRepository.findAll();
		List<Almacenamiento> almacenamientosFinal = new ArrayList<>();
		for (Almacenamiento almacenamiento : almacenamientos) {
			if (almacenamientoId.equals(almacenamiento.getCategoria().getCategoriaId())) {
				almacenamientosFinal.add(almacenamiento);
			}
		}
	
		return almacenamientosFinal;
	}

}
