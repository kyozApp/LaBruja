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

import com.labrujastore.entity.Marca;
import com.labrujastore.service.MarcaService;

@Controller
@RequestMapping("/admin")
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/marca")
    public String index(Model model) {
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("tablaMarca", marcas);
        return "admin/marca/index";
    }

    @GetMapping("/marca/crear")
    public String crear(Model model) {
        Marca marca = new Marca();
        model.addAttribute("formularioCrearMarca", marca);
        return "admin/marca/crear";
    }

    @PostMapping("/marca/crear")
    public String crear(@ModelAttribute Marca marca,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        marca.setImagenNombre(imagen.getOriginalFilename());
        marca.setImagenArchivo(imagen.getBytes());
        marcaService.guardarMarca(marca);
        return "redirect:/admin/marca";
    }

    @GetMapping("/marca/editar/{marcaId}")
    public String editar(@PathVariable Integer marcaId, Model model) {
        Marca marca = marcaService.obtenerIdMarca(marcaId);
        model.addAttribute("marca", marca);
        return "admin/marca/editar";
    }

    @PostMapping("/marca/editar/{marcaId}")
    public String editar(@PathVariable Integer marcaId, @ModelAttribute Marca marca,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        Marca marcarExistente = marcaService.obtenerIdMarca(marcaId);
        marcarExistente.setNombre(marca.getNombre());
        marcarExistente.setNombreUrl(marca.getNombreUrl());
        if (!imagen.isEmpty()) {
            marcarExistente.setImagenNombre(imagen.getOriginalFilename());
            marcarExistente.setImagenArchivo(imagen.getBytes());
        }
        marcaService.guardarMarca(marcarExistente);
        return "redirect:/admin/marca";
    }

    @GetMapping("/marca/{marcaId}")
    public String eliminar(@PathVariable Integer marcaId) {
        marcaService.eliminarMarca(marcaId);
        return "redirect:/admin/marca";
    }
}
