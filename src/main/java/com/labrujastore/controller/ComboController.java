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
import com.labrujastore.entity.Combo;
import com.labrujastore.entity.Marca;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.ComboService;
import com.labrujastore.service.MarcaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ComboController {

    @Autowired
    private ComboService comboService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/combo")
    public String index(Model model) {
        List<Combo> combos = comboService.listarCombo();
        model.addAttribute("tablaCombo", combos);
        return "admin/combo/index";
    }

    @GetMapping("/combo/crear")
    public String crear(Model model) {
        Combo combo = new Combo();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearCombo", combo);
        model.addAttribute("selectorCategorias", categorias);

        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/combo/crear";
    }

    @PostMapping("/combo/crear")
    public String crear(@ModelAttribute Combo combo,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        combo.setImagenNombre(imagen.getOriginalFilename());
        combo.setImagenArchivo(imagen.getBytes());
        comboService.guardarCombo(combo);
        return "redirect:/admin/combo";
    }

    @GetMapping("/combo/editar/{comboId}")
    public String editar(@PathVariable Integer comboId, Model model) {
        Combo combo = comboService.obtenerIdCombo(comboId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("combo", combo);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/combo/editar";
    }

    @PostMapping("/combo/editar/{comboId}")
    public String editar(
            @PathVariable Integer comboId,
            @ModelAttribute Combo combo,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("marcaId") Integer marcaId) throws IOException {
        Combo comboExistente = comboService.obtenerIdCombo(comboId);
        comboExistente.setNombre(combo.getNombre());
        comboExistente.setStock(combo.getStock());
        comboExistente.setStock_lima(stock_lima);
        comboExistente.setStock_arequipa(stock_arequipa);
        comboExistente.setPrecio(combo.getPrecio());
        comboExistente.setDescripcion(combo.getDescripcion());
        comboExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        comboExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

        if (!imagen.isEmpty()) {
            comboExistente.setImagenNombre(imagen.getOriginalFilename());
            comboExistente.setImagenArchivo(imagen.getBytes());
        }
        comboService.guardarCombo(comboExistente);
        return "redirect:/admin/combo";
    }

    @GetMapping("/combo/{comboId}")
    public String eliminar(@PathVariable Integer comboId) {
        comboService.eliminarCombo(comboId);
        return "redirect:/admin/combo";
    }

    // ATRIBUTOS
    @GetMapping("/combo/atributos/{comboId}")
    public String atributos_GET(Model model, @PathVariable Integer comboId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getCombo() != null && atributo_u.getCombo().getComboId() != null &&
                    atributo_u.getCombo().getComboId() == comboId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);

        return "/admin/combo/atributo/index";
    }

    @PostMapping("/combo/atributos/{comboId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer comboId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Combo combo = comboService.obtenerIdCombo(comboId);
        atributo_p.setCombo(combo);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/combo/atributos/{comboId}";
    }

    @GetMapping("/combo/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        return "/admin/combo/atributo/editar";
    }

    @PostMapping("/combo/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/combo/atributos/" + atributoExistente.getCombo().getComboId();
    }

    @GetMapping("/combo/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
