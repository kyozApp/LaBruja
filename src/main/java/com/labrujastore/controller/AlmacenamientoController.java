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

import com.labrujastore.entity.Almacenamiento;
import com.labrujastore.entity.Categoria;
import com.labrujastore.service.AlmacenamientoService;
import com.labrujastore.service.CategoriaService;

@Controller
@RequestMapping("/admin")
public class AlmacenamientoController {
	private static String storageLocation = "src/main/resources/static/admin/almacenamiento";

	@Autowired
	private AlmacenamientoService almacenamientoService;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping("/almacenamiento")
	public String index(Model model) {
		List<Almacenamiento> almacenamientos = almacenamientoService.listarAlmacenamiento();
		for (Almacenamiento almacenamiento : almacenamientos) {
			almacenamiento.setImagenArchivo(
					"http://localhost:8080//admin/almacenamiento/" + almacenamiento.getImagenNombre());
		}
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
	public String crear(@ModelAttribute("formularioCrearAlmacenamiento") Almacenamiento almacenamiento,
			@RequestPart("imagen") MultipartFile imagen) {
		try {
			// Generar un nombre único para el archivo de imagen
			String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

			// Establecer el nombre de la imagen en el accesorio
			almacenamiento.setImagenNombre(imagenNombre);

			// Obtener la ruta del archivo donde se almacenará la imagen
			Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

			// Copiar los bytes de la imagen al archivo en el sistema de archivos
			Files.copy(imagen.getInputStream(), filePath);

			// Construir la URL de la imagen
			String imageUrl = "http://localhost:8080//admin/almacenamiento/" + imagenNombre;

			// Establecer la URL de la imagen en el accesorio
			almacenamiento.setImagenArchivo(imageUrl);

			// Guardar el accesorio en la base de datos
			almacenamientoService.guardarAlmacenamiento(almacenamiento);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/almacenamiento";
	}

	@GetMapping("/almacenamiento/editar/{almacenamientoId}")
	public String editar(@PathVariable Integer almacenamientoId, Model model) {
		List<Categoria> categorias = categoriaService.listarCategoria();
		model.addAttribute("formularioEditarAlmacenamiento",
				almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId));
		model.addAttribute("selectorCategorias", categorias);
		return "admin/almacenamiento/editar";
	}

	@PostMapping("/almacenamiento/editar/{almacenamientoId}")
	public String editar(@PathVariable Integer almacenamientoId,
			@ModelAttribute("formularioEditarAlmacenamiento") Almacenamiento almacenamiento,
			@RequestPart("imagen") MultipartFile imagen) {
		try {
			Almacenamiento almacenamientoAnterior = almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId);

			// Verificar si se ha proporcionado una nueva imagen
			if (!imagen.isEmpty()) {
				// Eliminar la imagen anterior
				eliminarImagen(almacenamientoAnterior.getImagenNombre());

				// Generar un nombre único para el archivo de imagen
				String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

				// Establecer el nombre de la nueva imagen en el accesorio
				almacenamiento.setImagenNombre(imagenNombre);

				// Obtener la ruta del archivo donde se almacenará la nueva imagen
				Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

				// Copiar los bytes de la imagen al archivo en el sistema de archivos
				Files.copy(imagen.getInputStream(), filePath);

				// Construir la URL de la nueva imagen
				String imageUrl = "http://localhost:8080//admin/almacenamiento/" + imagenNombre;

				// Establecer la URL de la nueva imagen en el accesorio
				almacenamiento.setImagenArchivo(imageUrl);
			} else {
				// Si no se proporciona una nueva imagen, mantener la imagen anterior
				almacenamiento.setImagenNombre(almacenamientoAnterior.getImagenNombre());
				almacenamiento.setImagenArchivo(almacenamientoAnterior.getImagenArchivo());
			}

			// Actualizar el accesorio en la base de datos
			almacenamientoService.actualizarAlmacenamiento(almacenamiento);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "redirect:/admin/almacenamiento";
	}

	@GetMapping("/almacenamiento/{almacenamientoId}")
	public String eliminar(@PathVariable Integer almacenamientoId) {
	    Almacenamiento almacenamiento = almacenamientoService.obtenerIdAlmacenamiento(almacenamientoId);
	    
	    // Eliminar la imagen asociada al almacenamiento
	    eliminarImagen(almacenamiento.getImagenNombre());
	    
	    // Eliminar el almacenamiento de la base de datos
	    almacenamientoService.eliminarAlmacenamiento(almacenamientoId);
	    
	    return "redirect:/admin/almacenamiento";
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
