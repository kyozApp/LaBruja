package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Placa;
import com.labrujastore.repository.PlacaRepository;

@Service
public class PlacaServiceImpl implements PlacaService
{
	@Autowired
	private PlacaRepository placaRepository;

	@Override
	public List<Placa> listarPlaca() {
		return placaRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Placa guardarPlaca(Placa placa) {
		return placaRepository.save(placa);
	}

	@SuppressWarnings("null")
	@Override
	public Placa actualizarPlaca(Placa placa) {
		return placaRepository.save(placa);
	}

	@SuppressWarnings("null")
	@Override
	public Placa obtenerIdPlaca(Integer placaId) {
		return placaRepository.findById(placaId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarPlaca(Integer placaId) {
		placaRepository.deleteById(placaId);
	}

}
