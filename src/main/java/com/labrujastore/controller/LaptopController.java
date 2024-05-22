package com.labrujastore.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
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

import com.labrujastore.entity.Atributos;
import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Laptop;
import com.labrujastore.entity.Marca;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.LaptopService;
import com.labrujastore.service.MarcaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/laptop")
    public String index(Model model) {
        List<Laptop> laptops = laptopService.listarLaptop();
        model.addAttribute("tablaLaptop", laptops);
        return "admin/laptop/index";
    }

    @GetMapping("/laptop/crear")
    public String crear(Model model) {
        Laptop laptop = new Laptop();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearLaptop", laptop);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/laptop/crear";
    }

    @PostMapping("/laptop/crear")
    public String crear(@ModelAttribute Laptop laptop,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        laptop.setImagenNombre(imagen.getOriginalFilename());
        laptop.setImagenArchivo(imagen.getBytes());
        laptopService.guardarLaptop(laptop);
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/editar/{laptopId}")
    public String editar(@PathVariable Integer laptopId, Model model) {
        Laptop laptop = laptopService.obtenerIdLaptop(laptopId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("laptop", laptop);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/laptop/editar";
    }

    @PostMapping("/laptop/editar/{laptopId}")
    public String editar(@PathVariable Integer laptopId, @ModelAttribute Laptop laptop,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url, @RequestParam("estado") String estado,
            @RequestParam("oferta") String oferta,
            @RequestParam("categoriaId") Integer categoriaId,
            @RequestParam("marcaId") Integer marcaId
            ) throws IOException {
        Laptop laptopExistente = laptopService.obtenerIdLaptop(laptopId);
        laptopExistente.setNombre(laptop.getNombre());
        laptopExistente.setStock(stock);
        laptopExistente.setStock_lima(stock_lima);
        laptopExistente.setStock_arequipa(stock_arequipa);
        laptopExistente.setPrecio(precio);
        laptopExistente.setDescripcion(descripcion);
        laptopExistente.setUrl(url);
        laptopExistente.setEstado(estado);
        laptopExistente.setOferta(oferta);
        laptopExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
        laptopExistente.setMarca(marcaService.obtenerIdMarca(marcaId));
        if (!imagen.isEmpty()) {
            laptopExistente.setImagenNombre(imagen.getOriginalFilename());
            laptopExistente.setImagenArchivo(imagen.getBytes());
        }
        laptopService.guardarLaptop(laptopExistente);
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/{laptopId}")
    public String eliminar(@PathVariable Integer laptopId) {
        laptopService.eliminarLaptop(laptopId);
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/atributos/{laptopId}")
    public String atributos_GET(Model model, @PathVariable Integer laptopId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getLaptop() != null && atributo_u.getLaptop().getLaptopId() != null &&
                    atributo_u.getLaptop().getLaptopId() == laptopId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);

        return "/admin/accesorio/atributo/index";
    }

    @PostMapping("/laptop/atributos/{laptopId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer laptopId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Laptop laptop = laptopService.obtenerIdLaptop(laptopId);
        atributo_p.setLaptop(laptop);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/laptop/atributos/{laptopId}";
    }

    @GetMapping("/laptop/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        return "/admin/laptop/atributo/editar";
    }

    @PostMapping("/laptop/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/laptop/atributos/" + atributoExistente.getLaptop().getLaptopId();
    }

    @GetMapping("/laptop/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
