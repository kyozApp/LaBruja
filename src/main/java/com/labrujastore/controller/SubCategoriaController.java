package com.labrujastore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Subcategoria;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.SubCategoriaService;

@Controller
@RequestMapping("/admin")
public class SubCategoriaController 
{
    @Autowired
    private SubCategoriaService subCategoriaService;

	@Autowired
    private CategoriaService categoriaService;

    @GetMapping("/subcategoria")
	public String index(Model model) {
		model.addAttribute("tablaSubCategoria", subCategoriaService.listarSubCategoria());
		return "admin/subcategoria/index";
	}

    @GetMapping("/subcategoria/crear")
	public String crear(Model model) {
		Subcategoria subcategoria = new Subcategoria();
		List<Categoria> categorias = categoriaService.listarCategoria();
		model.addAttribute("formularioCrearSubCategoria", subcategoria);
		model.addAttribute("selectorCategorias", categorias);
		return "admin/subcategoria/crear";
	}

    @PostMapping("/subcategoria/crear")
	public String crear(@ModelAttribute("formularioCrearSubCategoria") Subcategoria subcategoria) {
		subCategoriaService.guardarSubCategoria(subcategoria);
		return "redirect:/admin/subcategoria";
	}

    @GetMapping("/subcategoria/editar/{subcategoriaId}")
	public String editar(@PathVariable Integer subcategoriaId, Model model) {
		Subcategoria subCategoria = subCategoriaService.obtenerIdSubCategoria(subcategoriaId);
		List<Categoria> categorias = categoriaService.listarCategoria();
		model.addAttribute("subcategoria", subCategoria);
		model.addAttribute("selectorCategorias", categorias);
		return "admin/subcategoria/editar";
	}

    // @PostMapping("/subcategoria/editar/{subcategoriaId}")
    // public String editar(
	// 	@PathVariable Integer procesadorId, 
	// 	@ModelAttribute Procesador procesador,
    //         @RequestParam("imagen") MultipartFile imagen, 
	// 		@RequestParam("stock") Integer stock,
    //         @RequestParam("precio") Double precio, 
	// 		@RequestParam("descripcion") String descripcion,
    //         @RequestParam("url") String url, 
	// 		@RequestParam("estado") String estado,
    //         @RequestParam("categoriaId") Integer categoriaId) throws IOException {
    //     Procesador procesadorExistente = procesadorService.obtenerIdProcesador(procesadorId);
    //     procesadorExistente.setNombre(procesador.getNombre());
    //     procesadorExistente.setStock(stock);
    //     procesadorExistente.setPrecio(precio);
    //     procesadorExistente.setDescripcion(descripcion);
    //     procesadorExistente.setUrl(url);
    //     procesadorExistente.setEstado(estado);
    //     procesadorExistente.setCategoria(categoriaService.obtenerIdCategoria(categoriaId));
    //     if (!imagen.isEmpty()) {
    //         procesadorExistente.setImagenNombre(imagen.getOriginalFilename());
    //         procesadorExistente.setImagenArchivo(imagen.getBytes());
    //     }
    //     procesadorService.guardarProcesador(procesadorExistente);
    //     return "redirect:/admin/procesador";
    // }

	@GetMapping("/subcategoria/{subcategoriaId}")
	public String eliminar(@PathVariable Integer subcategoriaId) {
		subCategoriaService.eliminarSubCategoria(subcategoriaId);
		return "redirect:/admin/subcategoria";
	}
}
