package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Fuente;
import com.labrujastore.repository.FuenteRepository;

@Service
public class FuenteServiceImpl implements FuenteService
{
	@Autowired
	private FuenteRepository fuenteRepository;

	@Override
	public List<Fuente> listarFuente() {
		return fuenteRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Fuente guardarFuente(Fuente fuente) {
		return fuenteRepository.save(fuente);
	}

	@SuppressWarnings("null")
	@Override
	public Fuente actualizarFuente(Fuente fuente) {
		return fuenteRepository.save(fuente);
	}

	@SuppressWarnings("null")
	@Override
	public Fuente obtenerIdFuente(Integer fuenteId) {
		return fuenteRepository.findById(fuenteId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarFuente(Integer fuenteId) {
		fuenteRepository.deleteById(fuenteId);
	}

}
