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
import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Categoria;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.CategoriaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class CasseController {
    @Autowired
    private CasseService casseService;

    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AtributosService atributoService;

    @GetMapping("/casse")
    public String index(Model model) {
        List<Casse> casses = casseService.listarCasse();
        model.addAttribute("tablaCasse", casses);
        return "admin/casse/index";
    }

    @GetMapping("/casse/crear")
    public String crear(Model model) {
        Casse casse = new Casse();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearCasse", casse);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/casse/crear";
    }

    @PostMapping("/casse/crear")
    public String crear(@ModelAttribute Casse casse,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        casse.setImagenNombre(imagen.getOriginalFilename());
        casse.setImagenArchivo(imagen.getBytes());
        casseService.guardarCasse(casse);
        return "redirect:/admin/casse";
    }

    @GetMapping("/casse/editar/{casseId}")
    public String editar(@PathVariable Integer casseId, Model model) {
        Casse casse = casseService.obtenerIdCasse(casseId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("casse", casse);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/casse/editar";
    }

    @PostMapping("/casse/editar/{casseId}")
    public String editar(@PathVariable Integer casseId, @ModelAttribute Casse casse,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId) throws IOException {
        Casse casseExistente = casseService.obtenerIdCasse(casseId);
        casseExistente.setNombre(casse.getNombre());
        casseExistente.setStock(stock);
        casseExistente.setStock_lima(stock_lima);
        casseExistente.setStock_arequipa(stock_arequipa);
        casseExistente.setPrecio(precio);
        casseExistente.setDescripcion(descripcion);
        casseExistente.setUrl(url);
        casseExistente.setEstado(estado);
        casseExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        if (!imagen.isEmpty()) {
            casseExistente.setImagenNombre(imagen.getOriginalFilename());
            casseExistente.setImagenArchivo(imagen.getBytes());
        }
        casseService.guardarCasse(casseExistente);
        return "redirect:/admin/casse";
    }

    @GetMapping("/casse/{casseId}")
    public String eliminar(@PathVariable Integer casseId) {
        casseService.eliminarCasse(casseId);
        return "redirect:/admin/casse";
    }

    @GetMapping("/casse/atributos/{casseId}")
    public String atributos_GET(Model model, @PathVariable Integer casseId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getCasse() != null && atributo_u.getCasse().getCasseId() != null &&
                    atributo_u.getCasse().getCasseId() == casseId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);

        return "/admin/casse/atributo/index";
    }

    @PostMapping("/casse/atributos/{casseId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer casseId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Casse casse = casseService.obtenerIdCasse(casseId);
        atributo_p.setCasse(casse);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/casse/atributos/{casseId}";
    }

    @GetMapping("/casse/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        return "/admin/casse/atributo/editar";
    }

    @PostMapping("/casse/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/casse/atributos/" + atributoExistente.getCasse().getCasseId();
    }

    @GetMapping("/casse/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
