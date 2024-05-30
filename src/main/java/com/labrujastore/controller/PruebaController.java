package com.labrujastore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Placa;
import com.labrujastore.entity.Procesador;
import com.labrujastore.entity.Ram;
import com.labrujastore.service.PlacaService;
import com.labrujastore.service.ProcesadorService;
import com.labrujastore.service.RamService;

@Controller
@RequestMapping("/prueba")
public class PruebaController 
{
    @Autowired
    private ProcesadorService procesadorService;

    @Autowired
    private PlacaService placaService;

    @Autowired
    private RamService ramService;

    @GetMapping("/cotizacion")
    public String getCotizacion(Model model) {
        // Obtener la lista de procesadores y agregarla al modelo
        List<Procesador> procesadores = procesadorService.listarProcesador();
        model.addAttribute("procesadores", procesadores);
        // Obtener la lista de placas y agregarla al modelo
        List<Placa> placas = placaService.listarPlaca();
        model.addAttribute("placas", placas);
        // Obtener los IDs de los procesadores compatibles con cada placa
        Map<Integer, List<Integer>> procesadoresCompatibles = new HashMap<>();
        for (Placa placa : placas) {
            Set<Procesador> procesadoresPlaca = placa.getItemsProcesador();
            List<Integer> idsProcesadores = procesadoresPlaca.stream().map(Procesador::getProcesadorId).collect(Collectors.toList());
            procesadoresCompatibles.put(placa.getPlacaId(), idsProcesadores);
        }
        model.addAttribute("procesadoresCompatibles", procesadoresCompatibles);

        // Asumiendo que ya tienes este código para recuperar las RAM y su compatibilidad con las placas
        List<Ram> rams = ramService.listarRam();
        model.addAttribute("rams", rams);
        Map<Integer, List<Integer>> placasCompatibles = new HashMap<>();
        for (Ram ram : rams) {
            Set<Placa> placasRams = ram.getItemsPlaca();
            List<Integer> idsPlacas = placasRams.stream().map(Placa::getPlacaId).collect(Collectors.toList());
            placasCompatibles.put(ram.getRamId(), idsPlacas);
        }
        model.addAttribute("placasCompatibles", placasCompatibles);


        return "cotizacion/prueba";
    }

}
