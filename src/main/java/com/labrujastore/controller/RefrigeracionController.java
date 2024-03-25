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
import com.labrujastore.entity.Refrigeracion;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.RefrigeracionService;

@Controller
@RequestMapping("/admin")
public class RefrigeracionController {

    private static String storageLocation = "src/main/resources/static/admin/refrigeracion";

    @Autowired
    private RefrigeracionService refrigeracionService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/refrigeracion")
    public String index(Model model) {
        List<Refrigeracion> refrigeraciones = refrigeracionService.listarRefrigeracion();
        for (Refrigeracion refrigeracion : refrigeraciones) {
            refrigeracion.setImagenArchivo("http://localhost:8080//admin/refrigeracion/" + refrigeracion.getImagenNombre());
        }
        model.addAttribute("tablaRefrigeracion", refrigeraciones);
        return "admin/refrigeracion/index";
    }

    @GetMapping("/refrigeracion/crear")
    public String crear(Model model) {
        Refrigeracion refrigeracion = new Refrigeracion();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearRefrigeracion", refrigeracion);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/refrigeracion/crear";
    }

    @PostMapping("/refrigeracion/crear")
    public String crear(@ModelAttribute("formularioCrearRefrigeracion") Refrigeracion refrigeracion,
                        @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en la refrigeración
            refrigeracion.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/refrigeracion/" + imagenNombre;

            // Establecer la URL de la imagen en la refrigeración
            refrigeracion.setImagenArchivo(imageUrl);

            // Guardar la refrigeración en la base de datos
            refrigeracionService.guardarRefrigeracion(refrigeracion);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/refrigeracion";
    }

    @GetMapping("/refrigeracion/editar/{refrigeracionId}")
    public String editar(@PathVariable Integer refrigeracionId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarRefrigeracion", refrigeracionService.obtenerIdRefrigeracion(refrigeracionId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/refrigeracion/editar";
    }

    @PostMapping("/refrigeracion/editar/{refrigeracionId}")
    public String editar(@PathVariable Integer refrigeracionId,
                         @ModelAttribute("formularioEditarRefrigeracion") Refrigeracion refrigeracion,
                         @RequestPart("imagen") MultipartFile imagen) {
        try {
            Refrigeracion refrigeracionAnterior = refrigeracionService.obtenerIdRefrigeracion(refrigeracionId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(refrigeracionAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en la refrigeración
                refrigeracion.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/refrigeracion/" + imagenNombre;

                // Establecer la URL de la nueva imagen en la refrigeración
                refrigeracion.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
                refrigeracion.setImagenNombre(refrigeracionAnterior.getImagenNombre());
                refrigeracion.setImagenArchivo(refrigeracionAnterior.getImagenArchivo());
            }

            // Actualizar la refrigeración en la base de datos
            refrigeracionService.actualizarRefrigeracion(refrigeracion);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/refrigeracion";
    }

    @GetMapping("/refrigeracion/{refrigeracionId}")
    public String eliminar(@PathVariable Integer refrigeracionId) {
        Refrigeracion refrigeracion = refrigeracionService.obtenerIdRefrigeracion(refrigeracionId);
        
        // Eliminar la imagen asociada a la refrigeración
        eliminarImagen(refrigeracion.getImagenNombre());
        
        // Eliminar la refrigeración de la base de datos
        refrigeracionService.eliminarRefrigeracion(refrigeracionId);
        
        return "redirect:/admin/refrigeracion";
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
