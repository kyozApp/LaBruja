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

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Categoria;
import com.labrujastore.service.AccesorioService;
import com.labrujastore.service.CategoriaService;

@Controller
@RequestMapping("/admin")
public class AccesorioController {
    @Autowired
    private AccesorioService accesorioService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/accesorio")
    public String index(Model model) {
        List<Accesorio> accesorios = accesorioService.listarAccesorio();
        model.addAttribute("tablaAccesorio", accesorios);
        return "admin/accesorio/index";
    }

    @GetMapping("/accesorio/crear")
    public String crear(Model model) {
        Accesorio accesorio = new Accesorio();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearAccesorio", accesorio);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/accesorio/crear";
    }

    @PostMapping("/accesorio/crear")
    public String crear(@ModelAttribute Accesorio accesorio,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        accesorio.setImagenNombre(imagen.getOriginalFilename());
        accesorio.setImagenArchivo(imagen.getBytes());
        accesorioService.guardarAccesorio(accesorio);
        return "redirect:/admin/accesorio";
    }

    @GetMapping("/accesorio/editar/{accesorioId}")
    public String editar(@PathVariable Integer accesorioId, Model model) {
        Accesorio accesorio = accesorioService.obtenerIdAccesorio(accesorioId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("accesorio", accesorio);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/accesorio/editar";
    }

    @PostMapping("/accesorio/editar/{accesorioId}")
    public String editar(@PathVariable Integer accesorioId, @ModelAttribute Accesorio accesorio,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Accesorio accesorioExistente = accesorioService.obtenerIdAccesorio(accesorioId);
        accesorioExistente.setNombre(accesorio.getNombre());
        accesorioExistente.setStock(stock);
        accesorioExistente.setPrecio(precio);
        accesorioExistente.setDescripcion(descripcion);
        accesorioExistente.setUrl(url);
        accesorioExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            accesorioExistente.setImagenNombre(imagen.getOriginalFilename());
            accesorioExistente.setImagenArchivo(imagen.getBytes());
        }
        accesorioService.guardarAccesorio(accesorioExistente);
        return "redirect:/admin/accesorio";
    }

    @GetMapping("/accesorio/{accesorioId}")
    public String eliminar(@PathVariable Integer accesorioId) {
        accesorioService.eliminarAccesorio(accesorioId);
        return "redirect:/admin/accesorio";
    }

}
