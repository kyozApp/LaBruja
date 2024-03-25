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
import com.labrujastore.entity.Fuente;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.FuenteService;

@Controller
@RequestMapping("/admin")
public class FuenteController 
{
	private static String storageLocation = "src/main/resources/static/admin/fuente";

    @Autowired
    private FuenteService fuenteService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/fuente")
    public String index(Model model) {
        List<Fuente> fuentes = fuenteService.listarFuente();
        for (Fuente fuente : fuentes) {
        	fuente.setImagenArchivo("http://localhost:8080//admin/fuente/" + fuente.getImagenNombre());
        }
        model.addAttribute("tablaFuente", fuentes);
        return "admin/fuente/index";
    }

    @GetMapping("/fuente/crear")
    public String crear(Model model) {
    	Fuente fuente = new Fuente();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearFuente", fuente);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/fuente/crear";
    }

    @PostMapping("/fuente/crear")
    public String crear(@ModelAttribute("formularioCrearFuente") Fuente fuente,
            @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en el accesorio
            fuente.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/fuente/" + imagenNombre;

            // Establecer la URL de la imagen en el accesorio
            fuente.setImagenArchivo(imageUrl);

            // Guardar el accesorio en la base de datos
            fuenteService.guardarFuente(fuente);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/fuente";
    }

    @GetMapping("/fuente/editar/{fuenteId}")
    public String editar(@PathVariable Integer fuenteId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarFuente", fuenteService.obtenerIdFuente(fuenteId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/fuente/editar";
    }

    @PostMapping("/fuente/editar/{fuenteId}")
    public String editar(@PathVariable Integer fuenteId,
            @ModelAttribute("formularioEditarFuente") Fuente fuente,
            @RequestPart("imagen") MultipartFile imagen) {
        try {
        	Fuente fuenteAnterior = fuenteService.obtenerIdFuente(fuenteId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(fuenteAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en el accesorio
                fuente.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/fuente/" + imagenNombre;

                // Establecer la URL de la nueva imagen en el accesorio
                fuente.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
            	fuente.setImagenNombre(fuenteAnterior.getImagenNombre());
            	fuente.setImagenArchivo(fuenteAnterior.getImagenArchivo());
            }

            // Actualizar el accesorio en la base de datos
            fuenteService.actualizarFuente(fuente);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/fuente";
    }

    @GetMapping("/fuente/{fuenteId}")
    public String eliminar(@PathVariable Integer fuenteId) {
        Fuente fuente = fuenteService.obtenerIdFuente(fuenteId);
        
        // Eliminar la imagen asociada a la fuente
        eliminarImagen(fuente.getImagenNombre());
        
        // Eliminar la fuente de la base de datos
        fuenteService.eliminarFuente(fuenteId);
        
        return "redirect:/admin/fuente";
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
