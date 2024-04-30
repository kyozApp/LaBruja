package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Banner;
import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Combo;
import com.labrujastore.entity.Fuente;
import com.labrujastore.entity.Laptop;
import com.labrujastore.entity.Monitor;
import com.labrujastore.entity.Placa;
import com.labrujastore.entity.Procesador;
// import com.labrujastore.entity.Producto;
import com.labrujastore.entity.Ram;
import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.AccesorioService;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.BannerService;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.ComboService;
import com.labrujastore.service.FuenteService;
import com.labrujastore.service.LaptopService;
import com.labrujastore.service.MonitorService;
import com.labrujastore.service.PlacaService;
import com.labrujastore.service.ProcesadorService;
// import com.labrujastore.service.ProductoService;
import com.labrujastore.service.RamService;
import com.labrujastore.service.RefrigeracionService;
import com.labrujastore.service.TarjetaService;
// import org.springframework.web.bind.annotation.RequestParam;
import com.labrujastore.util.TipoBanner;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @Autowired
    private BannerService bannerService;

    @Autowired
    private AccesorioService accesorioService;

    @Autowired
    private AlmacenamientoService almacenamientoService;

    @Autowired
    private CasseService casseService;

    @Autowired
    private FuenteService fuenteService;

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private PlacaService placaService;

    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private RamService ramService;

    @Autowired
    private RefrigeracionService refrigeracionService;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ComboService comboService;

    @GetMapping("producto")
    public String index(Model model) {

        model.addAttribute("catalogo", "CATALOGO");
        List<Banner> bannersCategoria = bannerService.obtenerBannersPorTipo(TipoBanner.CATEGORIA.getTipo());

        List<Accesorio> accesorios = accesorioService.listarAccesorio();
        List<Almacenamiento> almacenamientos = almacenamientoService.listarAlmacenamiento();
        List<Casse> casses = casseService.listarCasse();
        List<Fuente> fuentes = fuenteService.listarFuente();
        List<Laptop> laptops = laptopService.listarLaptop();
        List<Monitor> monitores = monitorService.listarMonitor();
        List<Placa> placas = placaService.listarPlaca();
        List<Procesador> procesadores = procesadorService.listarProcesador();
        List<Ram> rams = ramService.listarRam();
        List<Refrigeracion> refrigeraciones = refrigeracionService.listarRefrigeracion();
        List<Tarjeta> tarjetas = tarjetaService.listarTarjeta();
        List<Categoria> categorias = categoriaService.listarCategoria();
        List<Combo> combos = comboService.listarCombo();
        
        model.addAttribute("bannersCategoria", bannersCategoria);

        model.addAttribute("vistaAccesorios", accesorios);
        model.addAttribute("vistaAlmacenamientos", almacenamientos);
        model.addAttribute("vistaCasses", casses);
        model.addAttribute("vistaFuentes", fuentes);
        model.addAttribute("vistaLaptops", laptops);
        model.addAttribute("vistaMonitores", monitores);
        model.addAttribute("vistaPlacas", placas);
        model.addAttribute("vistaProcesadores", procesadores);
        model.addAttribute("vistaRams", rams);
        model.addAttribute("vistaRefrigeraciones", refrigeraciones);
        model.addAttribute("vistaTarjetas", tarjetas);
        model.addAttribute("vistaCategorias", categorias);
        model.addAttribute("vistaCombos", combos);

        return "catalogo/index";
    }

    @GetMapping("/{nombreUrl}")
    public String catalogo_categoria(Model model, @PathVariable String nombreUrl) {

        Categoria categoria = categoriaService.obtenerCategoriaNombreUrl(nombreUrl);

        model.addAttribute("nombreCategoria", categoria.getNombre());
        model.addAttribute("nombreURL", categoria.getNombreUrl());
        model.addAttribute("catalogo", categoria.getNombre());

        List<Banner> bannersCategoria = bannerService.obtenerBannersPorTipo(TipoBanner.CATEGORIA.getTipo());
        List<Accesorio> accesoriosCat = accesorioService.obtenerAccesoriosPorCategoria(categoria.getCategoriaId());
        List<Almacenamiento> almacenamientosCat = almacenamientoService
                .obtenerAlmacenamientosPorCategoria(categoria.getCategoriaId());
        List<Casse> cassesCat = casseService.obtenerCassesPorCategoria(categoria.getCategoriaId());
        List<Fuente> fuentesCat = fuenteService.obtenerFuentesPorCategoria(categoria.getCategoriaId());
        List<Laptop> laptopsCat = laptopService.obtenerLaptopsPorCategoria(categoria.getCategoriaId());
        List<Monitor> monitoresCat = monitorService.obtenerMonitoresPorCategoria(categoria.getCategoriaId());
        List<Placa> placasCat = placaService.obtenerPlacasPorCategoria(categoria.getCategoriaId());
        List<Procesador> procesadoresCat = procesadorService
                .obtenerProcesadoresPorCategoria(categoria.getCategoriaId());
        List<Ram> ramsCat = ramService.obtenerRamsPorCategoria(categoria.getCategoriaId());
        List<Refrigeracion> refrigeracionesCat = refrigeracionService
                .obtenerRefrigeracionesPorCategoria(categoria.getCategoriaId());
        List<Tarjeta> tarjetasCat = tarjetaService.obtenerTarjetasPorCategoria(categoria.getCategoriaId());
        List<Combo> combosCat = comboService.obtenerCombosPorCategoria(categoria.getCategoriaId());

        model.addAttribute("categoria", categoria);
        model.addAttribute("bannersCategoria", bannersCategoria);

        model.addAttribute("vistaAccesorios", accesoriosCat);
        model.addAttribute("vistaAlmacenamientos", almacenamientosCat);
        model.addAttribute("vistaCasses", cassesCat);
        model.addAttribute("vistaFuentes", fuentesCat);
        model.addAttribute("vistaLaptops", laptopsCat);
        model.addAttribute("vistaMonitores", monitoresCat);
        model.addAttribute("vistaPlacas", placasCat);
        model.addAttribute("vistaProcesadores", procesadoresCat);
        model.addAttribute("vistaRams", ramsCat);
        model.addAttribute("vistaRefrigeraciones", refrigeracionesCat);
        model.addAttribute("vistaTarjetas", tarjetasCat);
        model.addAttribute("vistaCombos", combosCat);

        return "catalogo/index";
    }

    @GetMapping("/producto-detalle")
    public String detalle_Producto() {
        return "producto-detalle/index";
    }

}
