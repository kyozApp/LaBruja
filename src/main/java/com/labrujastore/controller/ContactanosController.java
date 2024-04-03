package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Categoria;
import com.labrujastore.service.CategoriaService;

import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/contactanos")
public class ContactanosController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("")
    public String index_GET(Model model) {

        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("vistaCategorias", categorias);

        return "contactanos/index";
    }
    
}
