package com.labrujastore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Atributos;
import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Marca;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class AlmacenamientoController {
    @Autowired
    private AlmacenamientoService almacenamientoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/almacenamiento")
    public String index(Model model) {
        List<Almacenamiento> almacenamientos = almacenamientoService.listarAlmacenamiento();
        model.addAttribute("tablaAlmacenamiento", almacenamientos);
        return "admin/almacenamiento/index";
    }

    @GetMapping("/almacenamiento/crear")
    public String crear(Model model) {
        Almacenamiento almacenamiento = new Almacenamiento();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearAlmacenamiento", almacenamiento);
        model.addAttribute("selectorCategorias", categorias);

        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/almacenamiento/crear";
    }

    @PostMapping("/almacenamiento/crear")
    public String crear(@ModelAttribute Almacenamiento almacenamiento,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        almacenamiento.setImagenNombre(imagen.getOriginalFilename());
        almacenamiento.setImagenArchivo(imagen.getBytes());
        almacenamientoService.guardarAlmacenamiento(almacenamiento);
        return "redirect:/admin/almacenamiento";
    }

    @GetMapping("/almacenamiento/editar/{almacenamientoId}")
    public String editar(@PathVariable Integer almacenamientoId, Model model) {
        Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("almacenamiento", almacenamiento);
        model.addAttribute("selectorCategorias", categorias);

        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/almacenamiento/editar";
    }

    @PostMapping("/almacenamiento/editar/{almacenamientoId}")
    public String editar(@PathVariable Integer almacenamientoId, @ModelAttribute Almacenamiento almacenamiento,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("marcaId") Integer marcaId) throws IOException {
        Almacenamiento almacenamientoExistente = almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId);
        almacenamientoExistente.setNombre(almacenamiento.getNombre());
        almacenamientoExistente.setStock(stock);
        almacenamientoExistente.setStock_lima(stock_lima);
        almacenamientoExistente.setStock_arequipa(stock_arequipa);
        almacenamientoExistente.setPrecio(precio);
        almacenamientoExistente.setDescripcion(descripcion);
        almacenamientoExistente.setUrl(url);
        almacenamientoExistente.setEstado(estado);
        almacenamientoExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        almacenamientoExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

        if (!imagen.isEmpty()) {
            almacenamientoExistente.setImagenNombre(imagen.getOriginalFilename());
            almacenamientoExistente.setImagenArchivo(imagen.getBytes());
        }
        almacenamientoService.guardarAlmacenamiento(almacenamientoExistente);
        return "redirect:/admin/almacenamiento";
    }

    @GetMapping("/almacenamiento/{almacenamientoId}")
    public String eliminar(@PathVariable Integer almacenamientoId) {
        almacenamientoService.eliminarAlmacenamiento(almacenamientoId);
        return "redirect:/admin/almacenamiento";
    }

    // ATRIBUTOS
    @GetMapping("/almacenamiento/atributos/{productoId}")
    public String atributos_GET(Model model, @PathVariable Integer productoId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getAlmacenamiento() != null && atributo_u.getAlmacenamiento().getAlmacenamientoId() != null
                    &&
                    atributo_u.getAlmacenamiento().getAlmacenamientoId() == productoId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);
        model.addAttribute("categoria", "almacenamiento");

        return "/atributo/index";
    }

    @PostMapping("/almacenamiento/atributos/{productoId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer productoId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(productoId);
        atributo_p.setAlmacenamiento(almacenamiento);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/almacenamiento/atributos/{productoId}";
    }

    @GetMapping("/almacenamiento/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        model.addAttribute("categoria", "almacenamiento");
        model.addAttribute("categoriaId", atributo.getAlmacenamiento().getAlmacenamientoId());

        return "/atributo/editar";
    }

    @PostMapping("/almacenamiento/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/almacenamiento/atributos/"
                + atributoExistente.getAlmacenamiento().getAlmacenamientoId();
    }

    @GetMapping("/almacenamiento/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }
}
