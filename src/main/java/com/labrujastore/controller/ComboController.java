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

import com.labrujastore.entity.Combo;
import com.labrujastore.service.ComboService;

@Controller
@RequestMapping("/admin")
public class ComboController {
    @Autowired
    private ComboService comboService;

    @GetMapping("/combo")
    public String index(Model model) {
        List<Combo> combos = comboService.listarCombo();
        model.addAttribute("tablaCombo", combos);
        return "admin/combo/index";
    }

    @GetMapping("/combo/crear")
    public String crear(Model model) {
        Combo combo = new Combo();
        model.addAttribute("formularioCrearCombo", combo);
        return "admin/combo/crear";
    }

    @PostMapping("/combo/crear")
    public String crear(@ModelAttribute Combo combo,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        combo.setImagenNombre(imagen.getOriginalFilename());
        combo.setImagenArchivo(imagen.getBytes());
        comboService.guardarCombo(combo);
        return "redirect:/admin/combo";
    }

    @GetMapping("/combo/editar/{comboId}")
    public String editar(@PathVariable Integer comboId, Model model) {
        Combo combo = comboService.obtenerIdCombo(comboId);
        model.addAttribute("combo", combo);
        return "admin/combo/editar";
    }

    @PostMapping("/combo/editar/{comboId}")
    public String editar(@PathVariable Integer comboId, @ModelAttribute Combo combo,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        Combo comboExistente = comboService.obtenerIdCombo(comboId);
        comboExistente.setNombre(combo.getNombre());
        comboExistente.setDescripcion(combo.getDescripcion());
        if (!imagen.isEmpty()) {
            comboExistente.setImagenNombre(imagen.getOriginalFilename());
            comboExistente.setImagenArchivo(imagen.getBytes());
        }
        comboService.guardarCombo(comboExistente);
        return "redirect:/admin/combo";
    }

    @GetMapping("/combo/{comboId}")
    public String eliminar(@PathVariable Integer comboId) {
        comboService.eliminarCombo(comboId);
        return "redirect:/admin/combo";
    }
}
