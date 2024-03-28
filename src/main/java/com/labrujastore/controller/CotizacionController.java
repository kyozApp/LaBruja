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
public class CotizacionController {

    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private PlacaService placaService;

    @Autowired
    private RamService ramService;

    @Autowired
    private AlmacenamientoService almacenamientoService;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private FuenteService fuenteService;

    @Autowired
    private CasseService casseService;

    @Autowired
    private MonitorService monitorService;

    @Autowired
    private RefrigeracionService refrigeracionService;

    @Autowired
    private AccesorioService accesorioService;

    @GetMapping("/producto")
    public String index(Model model) {
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

        model.addAttribute("vistaAccesorios", accesorios);
        model.addAttribute("vistaAlmacenamientos", almacenamientos);
        model.addAttribute("vistaCasses", casses);
        model.addAttribute("vistaFuentes", fuentes);
        model.addAttribute("vistaMonitores", monitores);
        model.addAttribute("vistaPlacas", placas);
        model.addAttribute("vistaProcesadores", procesadores);
        model.addAttribute("vistaRams", rams);
        model.addAttribute("vistaRefrigeraciones", refrigeraciones);
        model.addAttribute("vistaTarjetas", tarjetas);
        return "cotizacion/index";
    }

}
