package com.labrujastore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Procesador;
import com.labrujastore.repository.ProcesadorRepository;

@Service
public class ProcesadorServiceImpl implements ProcesadorService
{
	@Autowired
	private ProcesadorRepository procesadorRepository;

	@Override
	public List<Procesador> listarProcesador() {
		return procesadorRepository.findAll();
	}

	@Override
	public Procesador guardarProcesador(Procesador procesador) {
		return procesadorRepository.save(procesador);
	}

	@Override
	public Procesador actualizarProcesador(Procesador procesador) {
		return procesadorRepository.save(procesador);
	}

	@Override
	public Procesador obtenerIdProcesador(Integer procesadorId) {
		return procesadorRepository.findById(procesadorId).get();
	}

	@Override
	public void eliminarProcesador(Integer procesadorId) {
		procesadorRepository.deleteById(procesadorId);
	}

	@Override
    public List<Procesador> obtenerProcesadoresPorCategoria(Integer procesadorId) {

        List<Procesador> procesadores = procesadorRepository.findAll();
        List<Procesador> procesadoresFinal = new ArrayList<>();
        for (Procesador procesador : procesadores) {
            if (procesadorId.equals(procesador.getCategoria().getCategoriaId())) {
                procesadoresFinal.add(procesador);
            }
        }

        return procesadoresFinal;
    }
    
    @Override
    public List<Procesador> obtenerProcesadoresPorMarca(Integer marcaId) {

        List<Procesador> procesadores = procesadorRepository.findAll();
        List<Procesador> procesadoresFinal = new ArrayList<>();
        for (Procesador procesador : procesadores) {
            if (procesador.getMarca() != null && marcaId.equals(procesador.getMarca().getMarcaId())) {
                procesadoresFinal.add(procesador);
            }
        }

        return procesadoresFinal;
    }
}
