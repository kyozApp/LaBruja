package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Fuente;
import com.labrujastore.entity.Monitor;
import com.labrujastore.entity.Placa;
import com.labrujastore.entity.Procesador;
import com.labrujastore.entity.Ram;
import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.AccesorioService;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.FuenteService;
import com.labrujastore.service.MonitorService;
import com.labrujastore.service.PlacaService;
import com.labrujastore.service.ProcesadorService;
import com.labrujastore.service.RamService;
import com.labrujastore.service.RefrigeracionService;
import com.labrujastore.service.TarjetaService;

@Controller
@RequestMapping("/cotizacion")
public class CotizacionController 
{
    @Autowired
    private AccesorioService accesorioService;

    @Autowired
    private AlmacenamientoService almacenamientoService;

    @Autowired
    private CasseService casseService;
    
    @Autowired
    private FuenteService fuenteService;

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

    @GetMapping("producto")
    public String index(Model model)
    {
        // Obtener los productos de cada tipo utilizando el servicio respectivo
        List<Accesorio> accesorios = accesorioService.listarAccesorio();
        List<Almacenamiento> almacenamientos = almacenamientoService.listarAlmacenamiento();
        List<Casse> casses = casseService.listarCasse();
        List<Fuente> fuentes = fuenteService.listarFuente();
        List<Monitor> monitores = monitorService.listarMonitor();
        List<Placa> placas = placaService.listarPlaca();
        List<Procesador> procesadores = procesadorService.listarProcesador();
        List<Ram> rams = ramService.listarRam();
        List<Refrigeracion> refrigeraciones = refrigeracionService.listarRefrigeracion();
        List<Tarjeta> tarjetas = tarjetaService.listarTarjeta();

        // Agregar las listas de productos al modelo
        model.addAttribute("accesorios", accesorios);
        model.addAttribute("almacenamientos", almacenamientos);
        model.addAttribute("casses", casses);
        model.addAttribute("fuentes", fuentes);
        model.addAttribute("monitores", monitores);
        model.addAttribute("placas", placas);
        model.addAttribute("procesadores", procesadores);
        model.addAttribute("rams", rams);
        model.addAttribute("refrigeraciones", refrigeraciones);
        model.addAttribute("tarjetas", tarjetas);
        
        return "cotizacion/index";
    }

    
}
