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
import com.labrujastore.entity.Monitor;
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MarcaService;
import com.labrujastore.service.MonitorService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributoService;

    @Autowired
    private MarcaService marcaService;

    @GetMapping("/monitor")
    public String index(Model model) {
        List<Monitor> monitores = monitorService.listarMonitor();
        model.addAttribute("tablaMonitor", monitores);
        return "admin/monitor/index";
    }

    @GetMapping("/monitor/crear")
    public String crear(Model model) {
        Monitor monitor = new Monitor();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearMonitor", monitor);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/monitor/crear";
    }

    @PostMapping("/monitor/crear")
    public String crear(@ModelAttribute Monitor monitor,
            @RequestParam("imagen") MultipartFile imagen) throws IOException {
        monitor.setImagenNombre(imagen.getOriginalFilename());
        monitor.setImagenArchivo(imagen.getBytes());
        monitorService.guardarMonitor(monitor);
        return "redirect:/admin/monitor";
    }

    @GetMapping("/monitor/editar/{monitorId}")
    public String editar(@PathVariable Integer monitorId, Model model) {
        Monitor monitor = monitorService.obtenerIdMonitor(monitorId);
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("monitor", monitor);
        model.addAttribute("selectorCategorias", categorias);
        
        List<Marca> marcas = marcaService.listarMarca();
        model.addAttribute("selectorMarcas", marcas);
        return "admin/monitor/editar";
    }

    @PostMapping("/monitor/editar/{monitorId}")
    public String editar(@PathVariable Integer monitorId, @ModelAttribute Monitor monitor,
            @RequestParam("imagen") MultipartFile imagen,
            @RequestParam("stock") Integer stock,
            @RequestParam("stock_lima") String stock_lima,
            @RequestParam("stock_arequipa") String stock_arequipa,
            @RequestParam("precio") Double precio,
            @RequestParam("descripcion") String descripcion,
            @RequestParam("url") String url,
            @RequestParam("estado") String estado,
            @RequestParam("marcaId") Integer marcaId
            ) throws IOException {
        Monitor monitorExistente = monitorService.obtenerIdMonitor(monitorId);
        monitorExistente.setNombre(monitor.getNombre());
        monitorExistente.setStock(stock);
        monitorExistente.setStock_lima(stock_lima);
        monitorExistente.setStock_arequipa(stock_arequipa);
        monitorExistente.setPrecio(precio);
        monitorExistente.setDescripcion(descripcion);
        monitorExistente.setUrl(url);
        monitorExistente.setEstado(estado);
        monitorExistente.setMarca(marcaService.obtenerIdMarca(marcaId));

        if (!imagen.isEmpty()) {
            monitorExistente.setImagenNombre(imagen.getOriginalFilename());
            monitorExistente.setImagenArchivo(imagen.getBytes());
        }
        monitorService.guardarMonitor(monitorExistente);
        return "redirect:/admin/monitor";
    }

    @GetMapping("/monitor/{monitorId}")
    public String eliminar(@PathVariable Integer monitorId) {
        monitorService.eliminarMonitor(monitorId);
        return "redirect:/admin/monitor";
    }

    // ATRIBUTOS
    @GetMapping("/monitor/atributos/{productoId}")
    public String atributos_GET(Model model, @PathVariable Integer productoId) {

        // CARGA EL FORMULARIO
        Atributos atributo = new Atributos();
        model.addAttribute("formularioAtributo", atributo);

        List<Atributos> todos_atributos = atributoService.listarAtributos();
        Collection<Atributos> atributos_tabla = new ArrayList<>();
        for (Atributos atributo_u : todos_atributos) {
            if (atributo_u.getMonitor() != null && atributo_u.getMonitor().getMonitorId() != null &&
                    atributo_u.getMonitor().getMonitorId() == productoId) {
                atributos_tabla.add(atributo_u);
            }
        }

        model.addAttribute("tablaAtributos", atributos_tabla);
        model.addAttribute("categoria", "monitor");

        return "admin/atributo/index";
    }

    @PostMapping("/monitor/atributos/{productoId}")
    public String atributos_POST(Model model, @ModelAttribute("formularioAtributo") Atributos atributo_p,
            @PathVariable Integer productoId) throws IOException {

        // PARA AGREGAR UN NUEVO ATRIBUTO
        Monitor monitor = monitorService.obtenerIdMonitor(productoId);
        atributo_p.setMonitor(monitor);
        atributoService.guardarAtributos(atributo_p);

        return "redirect:/admin/monitor/atributos/{productoId}";
    }

    @GetMapping("/monitor/atributos/editar/{atributoId}")
    public String atributo_editar_GET(Model model, @PathVariable Integer atributoId) {

        Atributos atributo = atributoService.obtenerIdAtributos(atributoId);
        model.addAttribute("atributo", atributo);

        model.addAttribute("categoria", "monitor");
        model.addAttribute("categoriaId", atributo.getMonitor().getMonitorId());

        return "admin/atributo/editar";
    }

    @PostMapping("/monitor/atributos/editar/{atributoId}")
    public String atributo_editar_POST(
            @PathVariable Integer atributoId,
            @ModelAttribute("atrubto") Atributos atributo_p,
            Model model) {

        Atributos atributoExistente = atributoService.obtenerIdAtributos(atributoId);
        atributoExistente.setTitulo(atributo_p.getTitulo());
        atributoExistente.setContenido(atributo_p.getContenido());
        atributoService.actualizarAtributos(atributoExistente);

        return "redirect:/admin/monitor/atributos/" + atributoExistente.getMonitor().getMonitorId();
    }

    @GetMapping("/monitor/atributos/eliminar/{atributoId}")
    public String atributo_eliminar_GET(@PathVariable Integer atributoId, HttpServletRequest request) {
        atributoService.eliminarAtributos(atributoId);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

}
