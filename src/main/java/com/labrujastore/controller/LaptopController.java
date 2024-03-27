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
import com.labrujastore.entity.Laptop;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.LaptopService;

@Controller
@RequestMapping("/admin")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/laptop")
    public String index(Model model) {
        List<Laptop> laptops = laptopService.listarLaptop();
        model.addAttribute("tablaLaptop", laptops);
        return "admin/laptop/index";
    }

    @GetMapping("/laptop/crear")
    public String crear(Model model) {
        Laptop laptop = new Laptop();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearLaptop", laptop);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/laptop/crear";
    }

    @PostMapping("/laptop/crear")
    public String crear(@ModelAttribute Laptop laptop,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        laptop.setImagenNombre(imagen.getOriginalFilename());
        laptop.setImagenArchivo(imagen.getBytes());
        laptopService.guardarLaptop(laptop);
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/editar/{laptopId}")
    public String editar(@PathVariable Integer laptopId, Model model) {
        Laptop laptop = laptopService.obtenerIdLaptop(laptopId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("laptop", laptop);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/laptop/editar";
    }

    @PostMapping("/laptop/editar/{laptopId}")
    public String editar(@PathVariable Integer laptopId, @ModelAttribute Laptop laptop,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Laptop laptopExistente = laptopService.obtenerIdLaptop(laptopId);
        laptopExistente.setNombre(laptop.getNombre());
        laptopExistente.setStock(stock);
        laptopExistente.setPrecio(precio);
        laptopExistente.setDescripcion(descripcion);
        laptopExistente.setUrl(url);
        laptopExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            laptopExistente.setImagenNombre(imagen.getOriginalFilename());
            laptopExistente.setImagenArchivo(imagen.getBytes());
        }
        laptopService.guardarLaptop(laptopExistente);
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/{laptopId}")
    public String eliminar(@PathVariable Integer laptopId) {
        laptopService.eliminarLaptop(laptopId);
        return "redirect:/admin/laptop";
    }
}
