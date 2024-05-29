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
import com.labrujastore.entity.Fuente;
import com.labrujastore.entity.Marca;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.FuenteService;
import com.labrujastore.service.MarcaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class FuenteController {
    @Autowired
    private FuenteService fuenteService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/fuente")
    public String index(Model model) {
        List<Fuente> fuentes = fuenteService.listarFuente();
        model.addAttribute("tablaFuente", fuentes);
        return "admin/fuente/index";
    }

    @GetMapping("/fuente/crear")
    public String crear(Model model) {
        Fuente fuente = new Fuente();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearFuente", fuente);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/fuente/crear";
    }

    @PostMapping("/fuente/crear")
    public String crear(@ModelAttribute Fuente fuente,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        fuente.setImagenNombre(imagen.getOriginalFilename());
        fuente.setImagenArchivo(imagen.getBytes());
        fuenteService.guardarFuente(fuente);
        return "redirect:/admin/fuente";
    }

    @GetMapping("/fuente/editar/{fuenteId}")
    public String editar(@PathVariable Integer fuenteId, Model model) {
        Fuente fuente = fuenteService.obtenerIdFuente(fuenteId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("fuente", fuente);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/fuente/editar";
    }

    @PostMapping("/fuente/editar/{fuenteId}")
    public String editar(@PathVariable Integer fuenteId, @ModelAttribute Fuente fuente,
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
        Fuente fuenteExistente = fuenteService.obtenerIdFuente(fuenteId);
        fuenteExistente.setNombre(fuente.getNombre());
        fuenteExistente.setStock(stock);
        fuenteExistente.setStock_lima(stock_lima);
        fuenteExistente.setStock_arequipa(stock_arequipa);
        fuenteExistente.setPrecio(precio);
        fuenteExistente.setDescripcion(descripcion);
        fuenteExistente.setUrl(url);
        fuenteExistente.setEstado(estado);
        fuenteExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        fuenteExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

        if (!imagen.isEmpty()) {
            fuenteExistente.setImagenNombre(imagen.getOriginalFilename());
            fuenteExistente.setImagenArchivo(imagen.getBytes());
        }
        fuenteService.guardarFuente(fuenteExistente);
        return "redirect:/admin/fuente";
    }

    @GetMapping("/fuente/{fuenteId}")
    public String eliminar(@PathVariable Integer fuenteId) {
        fuenteService.eliminarFuente(fuenteId);
        return "redirect:/admin/fuente";
    }

    // ATRIBUTOS
    @GetMapping("/fuente/atributos/{productoId}")
    public String atributos_GET(Model model, @PathVariable Integer productoId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getFuente() != null && atributo_u.getFuente().getFuenteId() != null &&
                    atributo_u.getFuente().getFuenteId() == productoId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);
        model.addAttribute("categoria", "fuente");

        return "admin/atributo/index";
    }

    @PostMapping("/fuente/atributos/{productoId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer productoId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Fuente fuente = fuenteService.obtenerIdFuente(productoId);
        atributo_p.setFuente(fuente);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/fuente/atributos/{productoId}";
    }

    @GetMapping("/fuente/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);
        model.addAttribute("categoria", "fuente");
        model.addAttribute("categoriaId", atributo.getFuente().getFuenteId());

        return "admin/atributo/editar";
    }

    @PostMapping("/fuente/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/fuente/atributos/" + atributoExistente.getFuente().getFuenteId();
    }

    @GetMapping("/fuente/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
