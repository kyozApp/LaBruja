package com.labrujastore.service;

import java.util.ArrayList;
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

	@Override
	public Placa guardarPlaca(Placa placa) {
		return placaRepository.save(placa);
	}

	@Override
	public Placa actualizarPlaca(Placa placa) {
		return placaRepository.save(placa);
	}

	@Override
	public Placa obtenerIdPlaca(Integer placaId) {
		return placaRepository.findById(placaId).get();
	}

	@Override
	public void eliminarPlaca(Integer placaId) {
		placaRepository.deleteById(placaId);
	}

	@Override
    public List<Placa> obtenerPlacasPorCategoria(Integer placaId) {

		List<Placa> placas = placaRepository.findAll();
		List<Placa> placasFinal = new ArrayList<>();
		for (Placa placa : placas) {
			if (placaId.equals(placa.getCategoria().getCategoriaId())) {
				placasFinal.add(placa);
			}
		}
	
		return placasFinal;
	}
}
