package com.labrujastore.service;

import java.util.List;

import com.labrujastore.entity.Marca;

public interface MarcaService {
    public List<Marca> listarMarca();

    public Marca guardarMarca(Marca marca);

    public Marca actualizarMarca(Marca marca);

    public Marca obtenerIdMarca(Integer marcaId);

    public void eliminarMarca(Integer marcaId);
}
