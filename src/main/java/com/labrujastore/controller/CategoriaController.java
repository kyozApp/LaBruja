package com.labrujastore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Categoria;
import com.labrujastore.service.CategoriaService;

@Controller
@RequestMapping("/admin")
public class CategoriaController {
	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/categoria")
	public String index(Model model) {
		model.addAttribute("tablaCategoria", categoriaService.listarCategoria());
		return "admin/categoria/index";
	}

	@GetMapping("/categoria/crear")
	public String crear(Model model) {
		Categoria categoria = new Categoria();
		model.addAttribute("formularioCrearCategoria", categoria);
		return "admin/categoria/crear";
	}

	@PostMapping("/categoria/crear")
	public String crear(@ModelAttribute("formularioCrearCategoria") Categoria categoria) {
		categoriaService.guardarCategoria(categoria);
		return "redirect:/admin/categoria";
	}

	@GetMapping("/categoria/editar/{categoriaId}")
	public String editar(@PathVariable Integer categoriaId, Model model) {
		model.addAttribute("formularioEditarCategoria", categoriaService.obtenerIdCategoria(categoriaId));
		return "admin/categoria/editar";
	}

	@PostMapping("/categoria/editar/{categoriaId}")
	public String editar(@PathVariable Integer categoriaId,
			@ModelAttribute("formularioEditarCategoria") Categoria categoria, Model model) {
		Categoria categoriaExistente = categoriaService.obtenerIdCategoria(categoriaId);
		categoriaExistente.setCategoriaId(categoriaId);
		categoriaExistente.setNombre(categoria.getNombre());
		categoriaService.actualizarCategoria(categoriaExistente);
		return "redirect:/admin/categoria";
	}

	@GetMapping("/categoria/{categoriaId}")
	public String eliminar(@PathVariable Integer categoriaId) {
		categoriaService.eliminarCategoria(categoriaId);
		return "redirect:/admin/categoria";
	}

}
