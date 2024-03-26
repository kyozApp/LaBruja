package com.labrujastore.controller;

import java.io.IOException;
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

import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Monitor;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MonitorService;

@Controller
@RequestMapping("/admin")
public class MonitorController {

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private CategoriaService categoriaService;

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
		model.addAttribute("categorias", categorias);
		return "admin/monitor/editar";
	}

	@PostMapping("/monitor/editar/{monitorId}")
	public String editar(@PathVariable Integer monitorId, @ModelAttribute Monitor monitor,
			@RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
			@RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
			@RequestParam("url") String url) throws IOException {
		Monitor monitorExistente = monitorService.obtenerIdMonitor(monitorId);
		monitorExistente.setNombre(monitor.getNombre());
		monitorExistente.setStock(stock);
		monitorExistente.setPrecio(precio);
		monitorExistente.setDescripcion(descripcion);
		monitorExistente.setUrl(url);
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

}
