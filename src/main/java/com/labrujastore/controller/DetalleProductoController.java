package com.labrujastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Producto;
import com.labrujastore.service.ProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequestMapping("/detalle-producto")
public class DetalleProductoController 
{
    @Autowired
    private ProductoService productoService;    

    @GetMapping("/{productoId}")
    public String detalles(@PathVariable Integer productoId, Model model) 
    {
        Producto producto = productoService.obtenerIdProducto(productoId);
        model.addAttribute("producto", producto);

        return "producto-detalle/index";
    }
    
}
