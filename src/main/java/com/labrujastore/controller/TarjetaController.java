package com.labrujastore.controller;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

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

import com.labrujastore.entity.Atributos;
import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Marca;
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;
import com.labrujastore.service.TarjetaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class TarjetaController {

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

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
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
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
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/tarjeta/editar";
    }

    @PostMapping("/tarjeta/editar/{tarjetaId}")
    public String editar(@PathVariable Integer tarjetaId, @ModelAttribute Tarjeta tarjeta,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("marcaId") Integer marcaId)
            throws IOException {
        Tarjeta tarjetaExistente = tarjetaService.obtenerIdTarjeta(tarjetaId);
        tarjetaExistente.setNombre(tarjeta.getNombre());
        tarjetaExistente.setStock(stock);
        tarjetaExistente.setStock_lima(stock_lima);
        tarjetaExistente.setStock_arequipa(stock_arequipa);
        tarjetaExistente.setPrecio(precio);
        tarjetaExistente.setDescripcion(descripcion);
        tarjetaExistente.setUrl(url);
        tarjetaExistente.setEstado(estado);
        tarjetaExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        tarjetaExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

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

    // ATRIBUTOS
    @GetMapping("/tarjeta/atributos/{productoId}")
    public String atributos_GET(Model model, @PathVariable Integer productoId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getTarjeta() != null && atributo_u.getTarjeta().getTarjetaId() != null &&
                    atributo_u.getTarjeta().getTarjetaId() == productoId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);
        model.addAttribute("categoria", "tarjeta");

        return "/admin/atributo/index";
    }

    @PostMapping("/tarjeta/atributos/{productoId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer productoId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Tarjeta tarjeta = tarjetaService.obtenerIdTarjeta(productoId);
        atributo_p.setTarjeta(tarjeta);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/tarjeta/atributos/{productoId}";
    }

    @GetMapping("/tarjeta/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        model.addAttribute("categoria", "tarjeta");
        model.addAttribute("categoriaId", atributo.getTarjeta().getTarjetaId());

        return "/admin/atributo/editar";
    }

    @PostMapping("/tarjeta/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/tarjeta/atributos/" + atributoExistente.getTarjeta().getTarjetaId();
    }

    @GetMapping("/tarjeta/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
