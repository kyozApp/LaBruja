package com.labrujastore.service;

import java.util.ArrayList;
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

	@Override
	public Fuente guardarFuente(Fuente fuente) {
		return fuenteRepository.save(fuente);
	}

	@Override
	public Fuente actualizarFuente(Fuente fuente) {
		return fuenteRepository.save(fuente);
	}

	@Override
	public Fuente obtenerIdFuente(Integer fuenteId) {
		return fuenteRepository.findById(fuenteId).get();
	}

	@Override
	public void eliminarFuente(Integer fuenteId) {
		fuenteRepository.deleteById(fuenteId);
	}

	@Override
    public List<Fuente> obtenerFuentesPorCategoria(Integer fuenteId) {

        List<Fuente> fuentes = fuenteRepository.findAll();
        List<Fuente> fuentesFinal = new ArrayList<>();
        for (Fuente fuente : fuentes) {
            if (fuenteId.equals(fuente.getCategoria().getCategoriaId())) {
                fuentesFinal.add(fuente);
            }
        }

        return fuentesFinal;
    }

    @Override
    public List<Fuente> obtenerFuentesPorMarca(Integer marcaId) {

        List<Fuente> fuentes = fuenteRepository.findAll();
        List<Fuente> fuentesFinal = new ArrayList<>();
        for (Fuente fuente : fuentes) {
            if (fuente.getMarca() != null && marcaId.equals(fuente.getMarca().getMarcaId())) {
                fuentesFinal.add(fuente);
            }
        }

        return fuentesFinal;
    }
}
