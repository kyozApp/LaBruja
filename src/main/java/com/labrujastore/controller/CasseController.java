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

import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Categoria;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.CategoriaService;

@Controller
@RequestMapping("/admin")
public class CasseController {
    @Autowired
    private CasseService casseService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/casse")
    public String index(Model model) {
        List<Casse> casses = casseService.listarCasse();
        model.addAttribute("tablaCasse", casses);
        return "admin/casse/index";
    }

    @GetMapping("/casse/crear")
    public String crear(Model model) {
        Casse casse = new Casse();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearCasse", casse);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/casse/crear";
    }

    @PostMapping("/casse/crear")
    public String crear(@ModelAttribute Casse casse,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        casse.setImagenNombre(imagen.getOriginalFilename());
        casse.setImagenArchivo(imagen.getBytes());
        casseService.guardarCasse(casse);
        return "redirect:/admin/casse";
    }

    @GetMapping("/casse/editar/{casseId}")
    public String editar(@PathVariable Integer casseId, Model model) {
        Casse casse = casseService.obtenerIdCasse(casseId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("casse", casse);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/casse/editar";
    }

    @PostMapping("/casse/editar/{casseId}")
    public String editar(@PathVariable Integer casseId, @ModelAttribute Casse casse,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Casse casseExistente = casseService.obtenerIdCasse(casseId);
        casseExistente.setNombre(casse.getNombre());
        casseExistente.setStock(stock);
        casseExistente.setPrecio(precio);
        casseExistente.setDescripcion(descripcion);
        casseExistente.setUrl(url);
        casseExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            casseExistente.setImagenNombre(imagen.getOriginalFilename());
            casseExistente.setImagenArchivo(imagen.getBytes());
        }
        casseService.guardarCasse(casseExistente);
        return "redirect:/admin/casse";
    }

    @GetMapping("/casse/{casseId}")
    public String eliminar(@PathVariable Integer casseId) {
        casseService.eliminarCasse(casseId);
        return "redirect:/admin/casse";
    }
}
