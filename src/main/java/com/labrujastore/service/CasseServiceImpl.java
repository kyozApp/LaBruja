package com.labrujastore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Casse;
import com.labrujastore.repository.CasseRepository;

@Service
public class CasseServiceImpl implements CasseService
{
	@Autowired
	private CasseRepository casseRepository;

	@Override
	public List<Casse> listarCasse() {
		return casseRepository.findAll();
	}

	@Override
	public Casse guardarCasse(Casse casse) {
		return casseRepository.save(casse);
	}

	@Override
	public Casse actualizarCasse(Casse casse) {
		return casseRepository.save(casse);
	}

	@Override
	public Casse obtenerIdCasse(Integer casseId) {
		return casseRepository.findById(casseId).get();
	}

	@Override
	public void eliminarCasse(Integer casseId) {
		casseRepository.deleteById(casseId);
	}

	@Override
    public List<Casse> obtenerCassesPorCategoria(Integer casseId) {

		List<Casse> casses = casseRepository.findAll();
		List<Casse> cassesFinal = new ArrayList<>();
		for (Casse casse : casses) {
			if (casseId.equals(casse.getCategoria().getCategoriaId())) {
				cassesFinal.add(casse);
			}
		}
	
		return cassesFinal;
	}
}
