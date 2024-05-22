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
import com.labrujastore.entity.Producto;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;
import com.labrujastore.service.ProductoService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/producto")
    public String index(Model model) {
        List<Producto> productos = productoService.listarProducto();
        model.addAttribute("bProducto", productos);

        return "admin/producto/index";
    }

    @GetMapping("/producto/crear")
    public String crear(Model model) {

        Producto producto = new Producto();
        List<Categoria> categorias = categoriaService.listarCategoria();

        model.addAttribute("formularioCrearProducto", producto);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/producto/crear";
    }

    @PostMapping("/producto/crear")
    public String crear(@ModelAttribute Producto producto,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        producto.setImagenNombre(imagen.getOriginalFilename());
        producto.setImagenArchivo(imagen.getBytes());
        productoService.guardarProducto(producto);

        return "redirect:/admin/producto";
    }

    @GetMapping("/producto/editar/{productoId}")
    public String editar(@PathVariable Integer productoId, Model model) {
        Producto producto = productoService.obtenerIdProducto(productoId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("producto", producto);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/producto/editar";
    }

    @PostMapping("/producto/editar/{productoId}")
    public String editar(@PathVariable Integer productoId, @ModelAttribute Producto producto,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("referencia") String referencia,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("marcaId") Integer marcaId)
            throws IOException {
        Producto productoExistente = productoService.obtenerIdProducto(productoId);
        productoExistente.setNombre(producto.getNombre());
        productoExistente.setStock(stock);
        productoExistente.setStock_lima(stock_lima);
        productoExistente.setStock_arequipa(stock_arequipa);
        productoExistente.setPrecio(precio);
        productoExistente.setReferencia(referencia);
        productoExistente.setDescripcion(descripcion);
        productoExistente.setUrl(url);
        productoExistente.setEstado(estado);
        productoExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        productoExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

        if (!imagen.isEmpty()) {
            productoExistente.setImagenNombre(imagen.getOriginalFilename());
            productoExistente.setImagenArchivo(imagen.getBytes());
        }
        productoService.guardarProducto(productoExistente);
        return "redirect:/admin/producto";
    }

    @GetMapping("/producto/{productoId}")
    public String eliminar(@PathVariable Integer productoId) {
        productoService.eliminarProducto(productoId);
        return "redirect:/admin/producto";
    }

    // ATRIBUTOS
    @GetMapping("/producto/atributos/{productoId}")
    public String atributos_GET(Model model, @PathVariable Integer productoId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getProducto() != null && atributo_u.getProducto().getProductoId() != null &&
                    atributo_u.getProducto().getProductoId() == productoId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);

        return "/admin/producto/atributo/index";
    }

    @PostMapping("/producto/atributos/{productoId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer productoId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Producto producto = productoService.obtenerIdProducto(productoId);
        atributo_p.setProducto(producto);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/producto/atributos/{productoId}";
    }

    @GetMapping("/producto/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        return "/admin/producto/atributo/editar";
    }

    @PostMapping("/producto/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/producto/atributos/" + atributoExistente.getProducto().getProductoId();
    }

    @GetMapping("/producto/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
