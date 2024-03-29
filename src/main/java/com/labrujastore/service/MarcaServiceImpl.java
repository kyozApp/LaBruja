package com.labrujastore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Marca;
import com.labrujastore.repository.MarcaRepository;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @SuppressWarnings("null")
    @Override
    public Marca actualizarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @SuppressWarnings("null")
    @Override
    public void eliminarMarca(Integer marcaId) {
        marcaRepository.deleteById(marcaId);
    }

    @SuppressWarnings("null")
    @Override
    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    @Override
    public List<Marca> listarMarca() {
        return marcaRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public Marca obtenerIdMarca(Integer marcaId) {
        return marcaRepository.findById(marcaId).get();
    }

}
