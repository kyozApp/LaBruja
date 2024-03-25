package com.labrujastore.service;

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

	@SuppressWarnings("null")
	@Override
	public Casse guardarCasse(Casse casse) {
		return casseRepository.save(casse);
	}

	@SuppressWarnings("null")
	@Override
	public Casse actualizarCasse(Casse casse) {
		return casseRepository.save(casse);
	}

	@SuppressWarnings("null")
	@Override
	public Casse obtenerIdCasse(Integer casseId) {
		return casseRepository.findById(casseId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarCasse(Integer casseId) {
		casseRepository.deleteById(casseId);
	}

}
