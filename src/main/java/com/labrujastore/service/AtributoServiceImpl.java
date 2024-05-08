package com.labrujastore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Atributos;
import com.labrujastore.repository.AtributosRepository;

@Service
public class AtributoServiceImpl implements AtributosService {

    @Autowired
    private AtributosRepository repository;

    @Override
    public List<Atributos> listarAtributos() {
        return repository.findAll();
    }

    @Override
    public Atributos guardarAtributos(Atributos atributo) {
        return repository.save(atributo);
    }

    @Override
    public Atributos actualizarAtributos(Atributos atributo) {
        return repository.save(atributo);
    }

    @Override
    public Atributos obtenerIdAtributos(Integer atributoId) {
        Optional<Atributos> atributoOptional = repository.findById(atributoId);
        return atributoOptional.orElse(null);
    }

    @Override
    public void eliminarAtributos(Integer atributoId) {
        repository.deleteById(atributoId);
    }

}
