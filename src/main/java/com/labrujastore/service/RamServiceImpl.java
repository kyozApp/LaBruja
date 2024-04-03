package com.labrujastore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Ram;
import com.labrujastore.repository.RamRepository;

@Service
public class RamServiceImpl implements RamService
{
	@Autowired
	private RamRepository ramRepository;

	@Override
	public List<Ram> listarRam() {
		return ramRepository.findAll();
	}

	@SuppressWarnings("null")
	@Override
	public Ram guardarRam(Ram ram) {
		return ramRepository.save(ram);
	}

	@SuppressWarnings("null")
	@Override
	public Ram actualizarRam(Ram ram) {
		return ramRepository.save(ram);
	}

	@SuppressWarnings("null")
	@Override
	public Ram obtenerIdRam(Integer ramId) {
		return ramRepository.findById(ramId).get();
	}

	@SuppressWarnings("null")
	@Override
	public void eliminarRam(Integer ramId) {
		ramRepository.deleteById(ramId);
	}

	@Override
    public List<Ram> obtenerRamsPorCategoria(Integer ramId) {

		List<Ram> rams = ramRepository.findAll();
		List<Ram> ramsFinal = new ArrayList<>();
		for (Ram ram : rams) {
			if (ramId.equals(ram.getCategoria().getCategoriaId())) {
				ramsFinal.add(ram);
			}
		}
	
		return ramsFinal;
	}
}
