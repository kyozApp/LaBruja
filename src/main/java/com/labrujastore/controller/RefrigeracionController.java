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
import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.RefrigeracionService;

@Controller
@RequestMapping("/admin")
public class RefrigeracionController {

    @Autowired
    private RefrigeracionService refrigeracionService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/refrigeracion")
    public String index(Model model) {
        List<Refrigeracion> refrigeraciones = refrigeracionService.listarRefrigeracion();
        model.addAttribute("tablaRefrigeracion", refrigeraciones);
        return "admin/refrigeracion/index";
    }

    @GetMapping("/refrigeracion/crear")
    public String crear(Model model) {
        Refrigeracion refrigeracion = new Refrigeracion();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearRefrigeracion", refrigeracion);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/refrigeracion/crear";
    }

    @PostMapping("/refrigeracion/crear")
    public String crear(@ModelAttribute Refrigeracion refrigeracion,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        refrigeracion.setImagenNombre(imagen.getOriginalFilename());
        refrigeracion.setImagenArchivo(imagen.getBytes());
        refrigeracionService.guardarRefrigeracion(refrigeracion);
        return "redirect:/admin/refrigeracion";
    }

    @GetMapping("/refrigeracion/editar/{refrigeracionId}")
    public String editar(@PathVariable Integer refrigeracionId, Model model) {
        Refrigeracion refrigeracion = refrigeracionService.obtenerIdRefrigeracion(refrigeracionId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("refrigeracion", refrigeracion);
        model.addAttribute("selectorCateogiras", categorias);
        return "admin/refrigeracion/editar";
    }

    @PostMapping("/refrigeracion/editar/{refrigeracionId}")
    public String editar(@PathVariable Integer refrigeracionId, @ModelAttribute Refrigeracion refrigeracion,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Refrigeracion refrigeracionExistente = refrigeracionService.obtenerIdRefrigeracion(refrigeracionId);
        refrigeracionExistente.setNombre(refrigeracion.getNombre());
        refrigeracionExistente.setStock(stock);
        refrigeracionExistente.setPrecio(precio);
        refrigeracionExistente.setDescripcion(descripcion);
        refrigeracionExistente.setUrl(url);
        refrigeracionExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            refrigeracionExistente.setImagenNombre(imagen.getOriginalFilename());
            refrigeracionExistente.setImagenArchivo(imagen.getBytes());
        }
        refrigeracionService.guardarRefrigeracion(refrigeracionExistente);
        return "redirect:/admin/refrigeracion";
    }

    @GetMapping("/refrigeracion/{refrigeracionId}")
    public String eliminar(@PathVariable Integer refrigeracionId) {
        refrigeracionService.eliminarRefrigeracion(refrigeracionId);
        return "redirect:/admin/refrigeracion";
    }
}
