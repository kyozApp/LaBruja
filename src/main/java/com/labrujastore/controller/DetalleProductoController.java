package com.labrujastore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.labrujastore.entity.Accesorio;
import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Atributos;
import com.labrujastore.entity.Casse;
import com.labrujastore.entity.Categoria;
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
import com.labrujastore.service.AtributosService;
import com.labrujastore.service.CasseService;
import com.labrujastore.service.CategoriaService;
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

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private AtributosService atributosService;

    @GetMapping("/producto/{id}")
    public String detalles(Model model, @PathVariable Integer id, @RequestParam("tipo") String tipo) {
        List<Atributos> todos_atributos = atributosService.listarAtributos();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("vistaCategorias", categorias);

        if ("accesorio".equals(tipo)) {
            Accesorio accesorio = accesorioService.obtenerIdAccesorio(id);
            model.addAttribute("accesorio", accesorio);
            model.addAttribute("leermas", accesorio.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getAccesorio() != null && atributo.getAccesorio().getAccesorioId() != null &&
                        atributo.getAccesorio().getAccesorioId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);

        } else if ("almacenamiento".equals(tipo)) {
            Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(id);
            model.addAttribute("almacenamiento", almacenamiento);
            model.addAttribute("leermas", almacenamiento.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getAlmacenamiento() != null && atributo.getAlmacenamiento().getAlmacenamientoId() != null
                        &&
                        atributo.getAlmacenamiento().getAlmacenamientoId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("casse".equals(tipo)) {
            Casse casse = casseService.obtenerIdCasse(id);
            model.addAttribute("casse", casse);
            model.addAttribute("leermas", casse.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getCasse() != null && atributo.getCasse().getCasseId() != null
                        &&
                        atributo.getCasse().getCasseId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("fuente".equals(tipo)) {
            Fuente fuente = fuenteService.obtenerIdFuente(id);
            model.addAttribute("fuente", fuente);
            model.addAttribute("leermas", fuente.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getFuente() != null && atributo.getFuente().getFuenteId() != null
                        &&
                        atributo.getFuente().getFuenteId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("laptop".equals(tipo)) {
            Laptop laptop = laptopService.obtenerIdLaptop(id);
            model.addAttribute("laptop", laptop);
            model.addAttribute("leermas", laptop.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getLaptop() != null && atributo.getLaptop().getLaptopId() != null &&
                        atributo.getLaptop().getLaptopId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);

        } else if ("monitor".equals(tipo)) {
            Monitor monitor = monitorService.obtenerIdMonitor(id);
            model.addAttribute("monitor", monitor);
            model.addAttribute("leermas", monitor.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getMonitor() != null && atributo.getMonitor().getMonitorId() != null
                        &&
                        atributo.getMonitor().getMonitorId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("placa".equals(tipo)) {
            Placa placa = placaService.obtenerIdPlaca(id);
            model.addAttribute("placa", placa);
            model.addAttribute("leermas", placa.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getPlaca() != null && atributo.getPlaca().getPlacaId() != null
                        &&
                        atributo.getPlaca().getPlacaId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("procesador".equals(tipo)) {
            Procesador procesador = procesadorService.obtenerIdProcesador(id);
            model.addAttribute("procesador", procesador);
            model.addAttribute("leermas", procesador.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getProcesador() != null && atributo.getProcesador().getProcesadorId() != null
                        &&
                        atributo.getProcesador().getProcesadorId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("ram".equals(tipo)) {
            Ram ram = ramService.obtenerIdRam(id);
            model.addAttribute("ram", ram);
            model.addAttribute("leermas", ram.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getRam() != null && atributo.getRam().getRamId() != null
                        &&
                        atributo.getRam().getRamId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("refrigeracion".equals(tipo)) {
            Refrigeracion refrigeracion = refrigeracionService.obtenerIdRefrigeracion(id);
            model.addAttribute("refrigeracion", refrigeracion);
            model.addAttribute("leermas", refrigeracion.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getRefrigeracion() != null && atributo.getRefrigeracion().getRefrigeracionId() != null
                        &&
                        atributo.getRefrigeracion().getRefrigeracionId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("tarjeta".equals(tipo)) {
            Tarjeta tarjeta = tarjetaService.obtenerIdTarjeta(id);
            model.addAttribute("tarjeta", tarjeta);
            model.addAttribute("leermas", tarjeta.getUrl());

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getTarjeta() != null && atributo.getTarjeta().getTarjetaId() != null
                        &&
                        atributo.getTarjeta().getTarjetaId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        } else if ("combo".equals(tipo)) {
            Combo combo = comboService.obtenerIdCombo(id);
            model.addAttribute("combo", combo);

            Collection<Atributos> atributos_tabla = new ArrayList<>();
            for (Atributos atributo : todos_atributos) {
                if (atributo.getCombo() != null && atributo.getCombo().getComboId() != null
                        &&
                        atributo.getCombo().getComboId() == id) {
                    atributos_tabla.add(atributo);
                }
            }
            model.addAttribute("listaAtrinutos", atributos_tabla);
        }
        return "producto-detalle/index";
    }

}
