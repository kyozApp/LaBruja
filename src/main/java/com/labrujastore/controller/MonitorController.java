package com.labrujastore.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.labrujastore.entity.Categoria;
import com.labrujastore.entity.Monitor;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.MonitorService;

@Controller
@RequestMapping("/admin")
public class MonitorController {

	private static String storageLocation = "src/main/resources/static/admin/monitor";

	@Autowired
	private MonitorService monitorService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/monitor")
	public String index(Model model) {
		List<Monitor> monitors = monitorService.listarMonitor();
		for (Monitor monitor : monitors) {
			monitor.setImagenArchivo("http://localhost:8080//admin/monitor/" + monitor.getImagenNombre());
		}
		model.addAttribute("tablaMonitor", monitors);
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
	public String crear(@ModelAttribute("formularioCrearMonitor") Monitor monitor,
			@RequestPart("imagen") MultipartFile imagen) {
		try {
			// Generar un nombre único para el archivo de imagen
			String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

			// Establecer el nombre de la imagen en el monitor
			monitor.setImagenNombre(imagenNombre);

			// Obtener la ruta del archivo donde se almacenará la imagen
			Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

			// Copiar los bytes de la imagen al archivo en el sistema de archivos
			Files.copy(imagen.getInputStream(), filePath);

			// Construir la URL de la imagen
			String imageUrl = "http://localhost:8080//admin/monitor/" + imagenNombre;

			// Establecer la URL de la imagen en el monitor
			monitor.setImagenArchivo(imageUrl);

			// Guardar el monitor en la base de datos
			monitorService.guardarMonitor(monitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/monitor";
	}

	@GetMapping("/monitor/editar/{monitorId}")
	public String editar(@PathVariable Integer monitorId, Model model) {
		List<Categoria> categorias = categoriaService.listarCategoria();
		model.addAttribute("formularioEditarMonitor", monitorService.obtenerIdMonitor(monitorId));
		model.addAttribute("selectorCategorias", categorias);
		return "admin/monitor/editar";
	}

	@PostMapping("/monitor/editar/{monitorId}")
	public String editar(@PathVariable Integer monitorId, @ModelAttribute("formularioEditarMonitor") Monitor monitor,
			@RequestPart("imagen") MultipartFile imagen) {
		try {
			Monitor monitorAnterior = monitorService.obtenerIdMonitor(monitorId);

			// Verificar si se ha proporcionado una nueva imagen
			if (!imagen.isEmpty()) {
				// Eliminar la imagen anterior
				eliminarImagen(monitorAnterior.getImagenNombre());

				// Generar un nombre único para el archivo de imagen
				String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

				// Establecer el nombre de la nueva imagen en el monitor
				monitor.setImagenNombre(imagenNombre);

				// Obtener la ruta del archivo donde se almacenará la nueva imagen
				Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

				// Copiar los bytes de la imagen al archivo en el sistema de archivos
				Files.copy(imagen.getInputStream(), filePath);

				// Construir la URL de la nueva imagen
				String imageUrl = "http://localhost:8080//admin/monitor/" + imagenNombre;

				// Establecer la URL de la nueva imagen en el monitor
				monitor.setImagenArchivo(imageUrl);
			} else {
				// Si no se proporciona una nueva imagen, mantener la imagen anterior
				monitor.setImagenNombre(monitorAnterior.getImagenNombre());
				monitor.setImagenArchivo(monitorAnterior.getImagenArchivo());
			}

			// Actualizar el monitor en la base de datos
			monitorService.actualizarMonitor(monitor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/monitor";
	}

	@GetMapping("/monitor/{monitorId}")
	public String eliminar(@PathVariable Integer monitorId) {
	    Monitor monitor = monitorService.obtenerIdMonitor(monitorId);
	    
	    // Eliminar la imagen asociada al monitor
	    eliminarImagen(monitor.getImagenNombre());
	    
	    // Eliminar el monitor de la base de datos
	    monitorService.eliminarMonitor(monitorId);
	    
	    return "redirect:/admin/monitor";
	}


	// Método para generar un nombre único para el archivo de imagen
	private String generarNombreUnico(String nombreOriginal) {
		String[] partesNombre = nombreOriginal.split("\\.");
		String extension = partesNombre[partesNombre.length - 1];
		String nombreBase = partesNombre[0];
		String timestamp = Long.toString(System.currentTimeMillis());
		return nombreBase + "_" + timestamp + "." + extension;
	}

	// Método para eliminar la imagen anterior
	private void eliminarImagen(String nombreImagen) {
		if (nombreImagen != null && !nombreImagen.isEmpty()) {
			Path imagePath = Paths.get(storageLocation + "/" + nombreImagen);
			try {
				Files.deleteIfExists(imagePath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
