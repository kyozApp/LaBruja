package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Placa;
import com.labrujastore.entity.Procesador;
import com.labrujastore.service.PlacaService;
import com.labrujastore.service.ProcesadorService;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {
    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private PlacaService placaService;

    @GetMapping("producto")
    public String index(Model model) {
        List<Procesador> procesadores = procesadorService.listarProcesador();
        List<Placa> placas = placaService.listarPlaca();
        model.addAttribute("vistaProcesadores", procesadores);
        model.addAttribute("vistaPlacas", placas);
        return "catalogo/index";
    }
}
