package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Atributos;

public interface AtributosService {
    List<Atributos> listarAtributos();

    Atributos guardarAtributos(Atributos atributo);

    Atributos actualizarAtributos(Atributos atributo);

    Atributos obtenerIdAtributos(Integer atributoId);

    void eliminarAtributos(Integer atributoId);
}