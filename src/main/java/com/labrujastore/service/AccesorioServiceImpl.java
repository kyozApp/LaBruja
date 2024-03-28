package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Accesorio;
import com.labrujastore.repository.AccesorioRepository;

@Service
public class AccesorioServiceImpl implements AccesorioService {
	@Autowired
	private AccesorioRepository accesorioRepository;

	@Override
	public List<Accesorio> listarAccesorio() {
		return accesorioRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Accesorio guardarAccesorio(Accesorio accesorio) {
		return accesorioRepository.save(accesorio);
	}

	@SuppressWarnings("null")
	@Override
	public Accesorio actualizarAccesorio(Accesorio accesorio) {
		return accesorioRepository.save(accesorio);
	}

	@SuppressWarnings("null")
	@Override
	public Accesorio obtenerIdAccesorio(Integer accesorioId) {
		return accesorioRepository.findById(accesorioId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarAccesorio(Integer accesorioId) {
		accesorioRepository.deleteById(accesorioId);
	}

}
