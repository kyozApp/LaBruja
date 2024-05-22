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

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Atributos;
import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Marca;
import com.labrujastore.service.AccesorioService;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AccesorioController {
    @Autowired
    private AccesorioService accesorioService;

    @Autowired
    private CategoriaService categoriaService;
    
    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/accesorio")
    public String index(Model model) {
        List<Accesorio> accesorios = accesorioService.listarAccesorio();
        model.addAttribute("tablaAccesorio", accesorios);
        return "admin/accesorio/index";
    }

    @GetMapping("/accesorio/crear")
    public String crear(Model model) {
        Accesorio accesorio = new Accesorio();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearAccesorio", accesorio);
        model.addAttribute("selectorCategorias", categorias);

        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/accesorio/crear";
    }

    @PostMapping("/accesorio/crear")
    public String crear(@ModelAttribute Accesorio accesorio,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        accesorio.setImagenNombre(imagen.getOriginalFilename());
        accesorio.setImagenArchivo(imagen.getBytes());
        accesorioService.guardarAccesorio(accesorio);
        return "redirect:/admin/accesorio";
    }

    @GetMapping("/accesorio/editar/{accesorioId}")
    public String editar(@PathVariable Integer accesorioId, Model model) {
        Accesorio accesorio = accesorioService.obtenerIdAccesorio(accesorioId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("accesorio", accesorio);
        model.addAttribute("selectorCategorias", categorias);

        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/accesorio/editar";
    }

    @PostMapping("/accesorio/editar/{accesorioId}")
    public String editar(@PathVariable Integer accesorioId, @ModelAttribute Accesorio accesorio,
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
        Accesorio accesorioExistente = accesorioService.obtenerIdAccesorio(accesorioId);
        accesorioExistente.setNombre(accesorio.getNombre());
        accesorioExistente.setStock(stock);
        accesorioExistente.setStock_lima(stock_lima);
        accesorioExistente.setStock_arequipa(stock_arequipa);
        accesorioExistente.setPrecio(precio);
        accesorioExistente.setDescripcion(descripcion);
        accesorioExistente.setUrl(url);
        accesorioExistente.setEstado(estado);
        accesorioExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        accesorioExistente.setMarca(marcaService.obtenerIdMarca(marcaId));
        if (!imagen.isEmpty()) {
            accesorioExistente.setImagenNombre(imagen.getOriginalFilename());
            accesorioExistente.setImagenArchivo(imagen.getBytes());
        }
        accesorioService.guardarAccesorio(accesorioExistente);
        return "redirect:/admin/accesorio";
    }

    @GetMapping("/accesorio/{accesorioId}")
    public String eliminar(@PathVariable Integer accesorioId) {
        accesorioService.eliminarAccesorio(accesorioId);
        return "redirect:/admin/accesorio";
    }

    // ATRIBUTOS
    @GetMapping("/accesorio/atributos/{accesorioId}")
    public String atributos_GET(Model model, @PathVariable Integer accesorioId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getAccesorio() != null && atributo_u.getAccesorio().getAccesorioId() != null &&
                    atributo_u.getAccesorio().getAccesorioId() == accesorioId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);

        return "/admin/accesorio/atributo/index";
    }

    @PostMapping("/accesorio/atributos/{accesorioId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer accesorioId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Accesorio accesorio = accesorioService.obtenerIdAccesorio(accesorioId);
        atributo_p.setAccesorio(accesorio);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/accesorio/atributos/{accesorioId}";
    }

    @GetMapping("/accesorio/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        return "/admin/accesorio/atributo/editar";
    }

    @PostMapping("/accesorio/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/accesorio/atributos/" + atributoExistente.getAccesorio().getAccesorioId();
    }

    @GetMapping("/accesorio/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
