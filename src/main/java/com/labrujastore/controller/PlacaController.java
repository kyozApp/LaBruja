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
import com.labrujastore.entity.Placa;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;
import com.labrujastore.service.PlacaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class PlacaController {

    @Autowired
    private PlacaService placaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

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
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
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
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/placa/editar";
    }

    @PostMapping("/placa/editar/{placaId}")
    public String editar(@PathVariable Integer placaId, @ModelAttribute Placa placa,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("marcaId") Integer marcaId
            ) throws IOException {
        Placa placaExistente = placaService.obtenerIdPlaca(placaId);
        placaExistente.setNombre(placa.getNombre());
        placaExistente.setStock(stock);
        placaExistente.setStock_lima(stock_lima);
        placaExistente.setStock_arequipa(stock_arequipa);
        placaExistente.setPrecio(precio);
        placaExistente.setDescripcion(descripcion);
        placaExistente.setUrl(url);
        placaExistente.setEstado(estado);
        placaExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        placaExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

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

    // ATRIBUTOS
    @GetMapping("/placa/atributos/{productoId}")
    public String atributos_GET(Model model, @PathVariable Integer productoId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getPlaca() != null && atributo_u.getPlaca().getPlacaId() != null &&
                    atributo_u.getPlaca().getPlacaId() == productoId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);
        model.addAttribute("categoria", "placa");

        return "/admin/atributo/index";
    }

    @PostMapping("/placa/atributos/{productoId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer productoId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Placa placa = placaService.obtenerIdPlaca(productoId);
        atributo_p.setPlaca(placa);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/placa/atributos/{productoId}";
    }

    @GetMapping("/placa/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        model.addAttribute("categoria", "placa");
        model.addAttribute("categoriaId", atributo.getPlaca().getPlacaId());

        return "/admin/atributo/editar";
    }

    @PostMapping("/placa/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/placa/atributos/" + atributoExistente.getPlaca().getPlacaId();
    }

    @GetMapping("/placa/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
