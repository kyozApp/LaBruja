package com.labrujastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("selectProcesador", procesadorService.listarProcesador());
        model.addAttribute("selectPlaca", placaService.listarPlaca());
        model.addAttribute("selectRam", ramService.listarRam());
        model.addAttribute("selectAlmacenamiento", almacenamientoService.listarAlmacenamiento());
        model.addAttribute("selectTarjeta", tarjetaService.listarTarjeta());
        model.addAttribute("selectFuente", fuenteService.listarFuente());
        model.addAttribute("selectCasse", casseService.listarCasse());
        model.addAttribute("selectMonitor", monitorService.listarMonitor());
        model.addAttribute("selectRefrigeracion", refrigeracionService.listarRefrigeracion());
        model.addAttribute("selectAccesorio", accesorioService.listarAccesorio());
        return "cotizacion/index";
    }

}
