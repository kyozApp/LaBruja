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
import com.labrujastore.entity.Placa;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.PlacaService;

@Controller
@RequestMapping("/admin")
public class PlacaController {

    @Autowired
    private PlacaService placaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/placa")
    public String index(Model model) {
        List<Placa> placas = placaService.listarPlaca();
        model.addAttribute("tablaPlaca", placas);
        return "admin/placa/index";
    }

    @GetMapping("/placa/crear")
    public String crear(Model model) {
        Placa placa = new Placa();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearPlaca", placa);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/placa/crear";
    }

    @PostMapping("/placa/crear")
    public String crear(@ModelAttribute Placa placa,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        placa.setImagenNombre(imagen.getOriginalFilename());
        placa.setImagenArchivo(imagen.getBytes());
        placaService.guardarPlaca(placa);
        return "redirect:/admin/placa";
    }

    @GetMapping("/placa/editar/{placaId}")
    public String editar(@PathVariable Integer placaId, Model model) {
        Placa placa = placaService.obtenerIdPlaca(placaId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("placa", placa);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/placa/editar";
    }

    @PostMapping("/placa/editar/{placaId}")
    public String editar(@PathVariable Integer placaId, @ModelAttribute Placa placa,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Placa placaExistente = placaService.obtenerIdPlaca(placaId);
        placaExistente.setNombre(placa.getNombre());
        placaExistente.setStock(stock);
        placaExistente.setPrecio(precio);
        placaExistente.setDescripcion(descripcion);
        placaExistente.setUrl(url);
        placaExistente.setEstado(estado);
        placaExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            placaExistente.setImagenNombre(imagen.getOriginalFilename());
            placaExistente.setImagenArchivo(imagen.getBytes());
        }
        placaService.guardarPlaca(placaExistente);
        return "redirect:/admin/placa";
    }

    @GetMapping("/placa/{placaId}")
    public String eliminar(@PathVariable Integer placaId) {
        placaService.eliminarPlaca(placaId);
        return "redirect:/admin/placa";
    }
}
