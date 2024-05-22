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
import com.labrujastore.entity.Procesador;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;
import com.labrujastore.service.ProcesadorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ProcesadorController {

    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

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
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
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
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/procesador/editar";
    }

    @PostMapping("/procesador/editar/{procesadorId}")
    public String editar(@PathVariable Integer procesadorId, @ModelAttribute Procesador procesador,
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
        Procesador procesadorExistente = procesadorService.obtenerIdProcesador(procesadorId);
        procesadorExistente.setNombre(procesador.getNombre());
        procesadorExistente.setStock(stock);
        procesadorExistente.setStock_lima(stock_lima);
        procesadorExistente.setStock_arequipa(stock_arequipa);
        procesadorExistente.setPrecio(precio);
        procesadorExistente.setDescripcion(descripcion);
        procesadorExistente.setUrl(url);
        procesadorExistente.setEstado(estado);
        procesadorExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        procesadorExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

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

    // ATRIBUTOS
    @GetMapping("/procesador/atributos/{procesadorId}")
    public String atributos_GET(Model model, @PathVariable Integer procesadorId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getProcesador() != null && atributo_u.getProcesador().getProcesadorId() != null &&
                    atributo_u.getProcesador().getProcesadorId() == procesadorId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);

        return "/admin/procesador/atributo/index";
    }

    @PostMapping("/procesador/atributos/{procesadorId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer procesadorId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Procesador procesador = procesadorService.obtenerIdProcesador(procesadorId);
        atributo_p.setProcesador(procesador);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/procesador/atributos/{procesadorId}";
    }

    @GetMapping("/procesador/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        return "/admin/procesador/atributo/editar";
    }

    @PostMapping("/procesador/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/procesador/atributos/" + atributoExistente.getProcesador().getProcesadorId();
    }

    @GetMapping("/procesador/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
