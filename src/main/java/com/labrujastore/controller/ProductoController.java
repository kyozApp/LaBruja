package com.labrujastore.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Producto;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.ProductoService;

@Controller
@RequestMapping("/admin")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/producto")
    public String index(Model model) {
        List<Producto> productos = productoService.listarProducto();
        model.addAttribute("bProducto", productos);

        return "admin/producto/index";
    }

    @GetMapping("/producto/crear")
    public String crear(Model model) {

        Producto producto = new Producto();
        List<Categoria> categorias = categoriaService.listarCategoria();

        model.addAttribute("formularioCrearProducto", producto);
        model.addAttribute("selectorCategorias", categorias);

        return "admin/producto/crear";
    }

    @PostMapping("/producto/crear")
    public String crear(@ModelAttribute Producto producto,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        producto.setImagenNombre(imagen.getOriginalFilename());
        producto.setImagenArchivo(imagen.getBytes());
        productoService.guardarProducto(producto);

        return "redirect:/admin/producto";
    }

    @GetMapping("/producto/editar/{productoId}")
    public String editar(@PathVariable Integer productoId, Model model) {
        Producto producto = productoService.obtenerIdProducto(productoId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("producto", producto);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/producto/editar";
    }

    @PostMapping("/producto/editar/{productoId}")
    public String editar(@PathVariable Integer productoId, @ModelAttribute Producto producto,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("referencia") String referencia,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Producto productoExistente = productoService.obtenerIdProducto(productoId);
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setStock(stock);
        productoExistente.setStock_lima(stock_lima);
        productoExistente.setStock_arequipa(stock_arequipa);
        productoExistente.setPrecio(precio);
        productoExistente.setReferencia(referencia);
        productoExistente.setDescripcion(descripcion);
        productoExistente.setUrl(url);
        productoExistente.setEstado(estado);
        productoExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            productoExistente.setImagenNombre(imagen.getOriginalFilename());
            productoExistente.setImagenArchivo(imagen.getBytes());
        }
        productoService.guardarProducto(productoExistente);
        return "redirect:/admin/producto";
    }

    @GetMapping("/producto/{productoId}")
    public String eliminar(@PathVariable Integer productoId) {
        productoService.eliminarProducto(productoId);
        return "redirect:/admin/producto";
    }

}
