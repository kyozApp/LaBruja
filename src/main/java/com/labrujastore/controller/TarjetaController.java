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
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.TarjetaService;

@Controller
@RequestMapping("/admin")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/tarjeta")
    public String index(Model model) {
        List<Tarjeta> tarjetas = tarjetaService.listarTarjeta();
        model.addAttribute("tablaTarjeta", tarjetas);
        return "admin/tarjeta/index";
    }

    @GetMapping("/tarjeta/crear")
    public String crear(Model model) {
        Tarjeta tarjeta = new Tarjeta();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearTarjeta", tarjeta);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/tarjeta/crear";
    }

    @PostMapping("/tarjeta/crear")
    public String crear(@ModelAttribute Tarjeta tarjeta,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        tarjeta.setImagenNombre(imagen.getOriginalFilename());
        tarjeta.setImagenArchivo(imagen.getBytes());
        tarjetaService.guardarTarjeta(tarjeta);
        return "redirect:/admin/tarjeta";
    }

    @GetMapping("/tarjeta/editar/{tarjetaId}")
    public String editar(@PathVariable Integer tarjetaId, Model model) {
        Tarjeta tarjeta = tarjetaService.obtenerIdTarjeta(tarjetaId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("tarjeta", tarjeta);
        model.addAttribute("categorias", categorias);
        return "admin/tarjeta/editar";
    }

    @PostMapping("/tarjeta/editar/{tarjetaId}")
    public String editar(@PathVariable Integer tarjetaId, @ModelAttribute Tarjeta tarjeta,
            @RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url) throws IOException {
        Tarjeta tarjetaExistente = tarjetaService.obtenerIdTarjeta(tarjetaId);
        tarjetaExistente.setNombre(tarjeta.getNombre());
        tarjetaExistente.setStock(stock);
        tarjetaExistente.setPrecio(precio);
        tarjetaExistente.setDescripcion(descripcion);
        tarjetaExistente.setUrl(url);
        if (!imagen.isEmpty()) {
            tarjetaExistente.setImagenNombre(imagen.getOriginalFilename());
            tarjetaExistente.setImagenArchivo(imagen.getBytes());
        }
        tarjetaService.guardarTarjeta(tarjetaExistente);
        return "redirect:/admin/tarjeta";
    }

    @GetMapping("/tarjeta/{tarjetaId}")
    public String eliminar(@PathVariable Integer tarjetaId) {
        tarjetaService.eliminarTarjeta(tarjetaId);
        return "redirect:/admin/tarjeta";
    }
}
