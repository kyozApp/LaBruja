package com.labrujastore.service;

import java.util.ArrayList;
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

	@Override
	public Accesorio guardarAccesorio(Accesorio accesorio) {
		return accesorioRepository.save(accesorio);
	}

	@Override
	public Accesorio actualizarAccesorio(Accesorio accesorio) {
		return accesorioRepository.save(accesorio);
	}

	@Override
	public Accesorio obtenerIdAccesorio(Integer accesorioId) {
		return accesorioRepository.findById(accesorioId).get();
	}

	@Override
	public void eliminarAccesorio(Integer accesorioId) {
		accesorioRepository.deleteById(accesorioId);
	}

	@Override
    public List<Accesorio> obtenerAccesoriosPorCategoria(Integer idCategoria) {

		List<Accesorio> accesorios = accesorioRepository.findAll();
		List<Accesorio> accesoriosFinal = new ArrayList<>();
		for (Accesorio accesorio : accesorios) {
			if (idCategoria.equals(accesorio.getCategoria().getCategoriaId())) {
				accesoriosFinal.add(accesorio);
			}
		}
	
		return accesoriosFinal;
	}

}
