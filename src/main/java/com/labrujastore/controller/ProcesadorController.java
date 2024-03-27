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
import com.labrujastore.entity.Procesador;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.ProcesadorService;

@Controller
@RequestMapping("/admin")
public class ProcesadorController {

    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/procesador")
    public String index(Model model) {
        List<Procesador> procesadores = procesadorService.listarProcesador();
        model.addAttribute("tablaProcesador", procesadores);
        return "admin/procesador/index";
    }

    @GetMapping("/procesador/crear")
    public String crear(Model model) {
        Procesador procesador = new Procesador();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearProcesador", procesador);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/procesador/crear";
    }

    @PostMapping("/procesador/crear")
    public String crear(@ModelAttribute Procesador procesador,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        procesador.setImagenNombre(imagen.getOriginalFilename());
        procesador.setImagenArchivo(imagen.getBytes());
        procesadorService.guardarProcesador(procesador);
        return "redirect:/admin/procesador";
    }

    @GetMapping("/procesador/editar/{procesadorId}")
    public String editar(@PathVariable Integer procesadorId, Model model) {
        Procesador procesador = procesadorService.obtenerIdProcesador(procesadorId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("procesador", procesador);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/procesador/editar";
    }

    @PostMapping("/procesador/editar/{procesadorId}")
    public String editar(@PathVariable Integer procesadorId, @ModelAttribute Procesador procesador,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Procesador procesadorExistente = procesadorService.obtenerIdProcesador(procesadorId);
        procesadorExistente.setNombre(procesador.getNombre());
        procesadorExistente.setStock(stock);
        procesadorExistente.setPrecio(precio);
        procesadorExistente.setDescripcion(descripcion);
        procesadorExistente.setUrl(url);
        procesadorExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            procesadorExistente.setImagenNombre(imagen.getOriginalFilename());
            procesadorExistente.setImagenArchivo(imagen.getBytes());
        }
        procesadorService.guardarProcesador(procesadorExistente);
        return "redirect:/admin/procesador";
    }

    @GetMapping("/procesador/{procesadorId}")
    public String eliminar(@PathVariable Integer procesadorId) {
        procesadorService.eliminarProcesador(procesadorId);
        return "redirect:/admin/procesador";
    }
}
