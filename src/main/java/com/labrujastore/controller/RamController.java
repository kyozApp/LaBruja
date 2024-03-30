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
import com.labrujastore.entity.Ram;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.RamService;

@Controller
@RequestMapping("/admin")
public class RamController {

    @Autowired
    private RamService ramService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/ram")
    public String index(Model model) {
        List<Ram> rams = ramService.listarRam();
        model.addAttribute("tablaRam", rams);
        return "admin/ram/index";
    }

    @GetMapping("/ram/crear")
    public String crear(Model model) {
        Ram ram = new Ram();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearRam", ram);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/ram/crear";
    }

    @PostMapping("/ram/crear")
    public String crear(@ModelAttribute Ram ram,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        ram.setImagenNombre(imagen.getOriginalFilename());
        ram.setImagenArchivo(imagen.getBytes());
        ramService.guardarRam(ram);
        return "redirect:/admin/ram";
    }

    @GetMapping("/ram/editar/{ramId}")
    public String editar(@PathVariable Integer ramId, Model model) {
        Ram ram = ramService.obtenerIdRam(ramId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("ram", ram);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/ram/editar";
    }

    @PostMapping("/ram/editar/{ramId}")
    public String editar(@PathVariable Integer ramId, @ModelAttribute Ram ram,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Ram ramExistente = ramService.obtenerIdRam(ramId);
        ramExistente.setNombre(ram.getNombre());
        ramExistente.setStock(stock);
        ramExistente.setPrecio(precio);
        ramExistente.setDescripcion(descripcion);
        ramExistente.setUrl(url);
        ramExistente.setEstado(estado);
        ramExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            ramExistente.setImagenNombre(imagen.getOriginalFilename());
            ramExistente.setImagenArchivo(imagen.getBytes());
        }
        ramService.guardarRam(ramExistente);
        return "redirect:/admin/ram";
    }

    @GetMapping("/ram/{ramId}")
    public String eliminar(@PathVariable Integer ramId) {
        ramService.eliminarRam(ramId);
        return "redirect:/admin/ram";
    }
}
