package com.labrujastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Combo;
import com.labrujastore.entity.Fuente;
import com.labrujastore.entity.Laptop;
import com.labrujastore.entity.Monitor;
import com.labrujastore.entity.Placa;
import com.labrujastore.entity.Procesador;
import com.labrujastore.entity.Ram;
import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.entity.Tarjeta;
import com.labrujastore.service.AccesorioService;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.ComboService;
import com.labrujastore.service.FuenteService;
import com.labrujastore.service.LaptopService;
import com.labrujastore.service.MonitorService;
import com.labrujastore.service.PlacaService;
import com.labrujastore.service.ProcesadorService;
import com.labrujastore.service.RamService;
import com.labrujastore.service.RefrigeracionService;
import com.labrujastore.service.TarjetaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/catalogo/detalle")
public class DetalleProductoController {
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
    private ComboService comboService;

    @GetMapping("/producto/{id}")
    public String detalles(Model model, @PathVariable Integer id, @RequestParam("tipo") String tipo) {
        if ("accesorio".equals(tipo)) {
            Accesorio accesorio = accesorioService.obtenerIdAccesorio(id);
            model.addAttribute("accesorio", accesorio);
        } else if ("almacenamiento".equals(tipo)) {
            Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(id);
            model.addAttribute("almacenamiento", almacenamiento);
        } else if ("casse".equals(tipo)) {
            Casse casse = casseService.obtenerIdCasse(id);
            model.addAttribute("casse", casse);
        } else if ("fuente".equals(tipo)) {
            Fuente fuente = fuenteService.obtenerIdFuente(id);
            model.addAttribute("fuente", fuente);
        } else if ("laptop".equals(tipo)) {
            Laptop laptop = laptopService.obtenerIdLaptop(id);
            model.addAttribute("laptop", laptop);
        } else if ("monitor".equals(tipo)) {
            Monitor monitor = monitorService.obtenerIdMonitor(id);
            model.addAttribute("monitor", monitor);
        } else if ("placa".equals(tipo)) {
            Placa placa = placaService.obtenerIdPlaca(id);
            model.addAttribute("placa", placa);
        } else if ("procesador".equals(tipo)) {
            Procesador procesador = procesadorService.obtenerIdProcesador(id);
            model.addAttribute("procesador", procesador);
        } else if ("ram".equals(tipo)) {
            Ram ram = ramService.obtenerIdRam(id);
            model.addAttribute("ram", ram);
        } else if ("refrigeracion".equals(tipo)) {
            Refrigeracion refrigeracion = refrigeracionService.obtenerIdRefrigeracion(id);
            model.addAttribute("refrigeracion", refrigeracion);
        } else if ("tarjeta".equals(tipo)) {
            Tarjeta tarjeta = tarjetaService.obtenerIdTarjeta(id);
            model.addAttribute("tarjeta", tarjeta);
        } else if ("combo".equals(tipo)) {
            Combo combo = comboService.obtenerIdCombo(id);
            model.addAttribute("combo", combo);
        }
        return "producto-detalle/index";
    }

}
