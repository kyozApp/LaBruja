package com.labrujastore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.labrujastore.entity.Producto;
// import com.labrujastore.repository.CategoriaRepository;
import com.labrujastore.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    // @Autowired
    // private CategoriaRepository categoriaRepository;

    @Override
    public List<Producto> listarProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto obtenerIdProducto(Integer productoId) {
        return productoRepository.findById(productoId).get();
    }

    @Override
    public void eliminarProducto(Integer productoId) {
        productoRepository.deleteById(productoId);
    }

    @Override
    public List<Producto> obtenerProductosPorCategoria(Integer idCategoria) {

        List<Producto> productos = productoRepository.findAll();
        List<Producto> productosFinal = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().getCategoriaId() == idCategoria) {
                productosFinal.add(producto);
            }
        }

        return productosFinal;
    }

}
