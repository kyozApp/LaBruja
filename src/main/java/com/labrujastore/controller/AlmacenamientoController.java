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

import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Categoria;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.CategoriaService;

@Controller
@RequestMapping("/admin")
public class AlmacenamientoController {
	@Autowired
	private AlmacenamientoService almacenamientoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/almacenamiento")
	public String index(Model model) {
		List<Almacenamiento> almacenamientos = almacenamientoService.listarAlmacenamiento();
		model.addAttribute("tablaAlmacenamiento", almacenamientos);
		return "admin/almacenamiento/index";
	}

	@GetMapping("/almacenamiento/crear")
	public String crear(Model model) {
		Almacenamiento almacenamiento = new Almacenamiento();
		List<Categoria> categorias = categoriaService.listarCategoria();
		model.addAttribute("formularioCrearAlmacenamiento", almacenamiento);
		model.addAttribute("selectorCategorias", categorias);
		return "admin/almacenamiento/crear";
	}

	@PostMapping("/almacenamiento/crear")
	public String crear(@ModelAttribute Almacenamiento almacenamiento,
			@RequestParam("imagen") MultipartFile imagen) throws IOException {
		almacenamiento.setImagenNombre(imagen.getOriginalFilename());
		almacenamiento.setImagenArchivo(imagen.getBytes());
		almacenamientoService.guardarAlmacenamiento(almacenamiento);
		return "redirect:/admin/almacenamiento";
	}

	@GetMapping("/almacenamiento/editar/{almacenamientoId}")
	public String editar(@PathVariable Integer almacenamientoId, Model model) {
		Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId);
		List<Categoria> categorias = categoriaService.listarCategoria();
		model.addAttribute("almacenamiento", almacenamiento);
		model.addAttribute("selectorCategorias", categorias);
		return "admin/almacenamiento/editar";
	}

	@PostMapping("/almacenamiento/editar/{almacenamientoId}")
	public String editar(@PathVariable Integer almacenamientoId, @ModelAttribute Almacenamiento almacenamiento,
			@RequestParam("imagen") MultipartFile imagen, @RequestParam("stock") Integer stock,
			@RequestParam("precio") Double precio, @RequestParam("descripcion") String descripcion,
			@RequestParam("url") String url, @RequestParam("categoriaId") Integer categoriaId) throws IOException {
		Almacenamiento almacenamientoExistente = almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId);
		almacenamientoExistente.setNombre(almacenamiento.getNombre());
		almacenamientoExistente.setStock(stock);
		almacenamientoExistente.setPrecio(precio);
		almacenamientoExistente.setDescripcion(descripcion);
		almacenamientoExistente.setUrl(url);
		almacenamientoExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
		if (!imagen.isEmpty()) {
			almacenamientoExistente.setImagenNombre(imagen.getOriginalFilename());
			almacenamientoExistente.setImagenArchivo(imagen.getBytes());
		}
		almacenamientoService.guardarAlmacenamiento(almacenamientoExistente);
		return "redirect:/admin/almacenamiento";
	}

	@GetMapping("/almacenamiento/{almacenamientoId}")
	public String eliminar(@PathVariable Integer almacenamientoId) {
		almacenamientoService.eliminarAlmacenamiento(almacenamientoId);
		return "redirect:/admin/almacenamiento";
	}
}
