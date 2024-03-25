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
import com.labrujastore.entity.Ram;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.RamService;

@Controller
@RequestMapping("/admin")
public class RamController {

    private static String storageLocation = "src/main/resources/static/admin/ram";

    @Autowired
    private RamService ramService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/ram")
    public String index(Model model) {
        List<Ram> rams = ramService.listarRam();
        for (Ram ram : rams) {
            ram.setImagenArchivo("http://localhost:8080//admin/ram/" + ram.getImagenNombre());
        }
        model.addAttribute("tablaRam", rams);
        return "admin/ram/index";
    }

    @GetMapping("/ram/crear")
    public String crear(Model model) {
        Ram ram = new Ram();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearRam", ram);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/ram/crear";
    }

    @PostMapping("/ram/crear")
    public String crear(@ModelAttribute("formularioCrearRam") Ram ram,
                        @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en la RAM
            ram.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/ram/" + imagenNombre;

            // Establecer la URL de la imagen en la RAM
            ram.setImagenArchivo(imageUrl);

            // Guardar la RAM en la base de datos
            ramService.guardarRam(ram);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/ram";
    }

    @GetMapping("/ram/editar/{ramId}")
    public String editar(@PathVariable Integer ramId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarRam", ramService.obtenerIdRam(ramId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/ram/editar";
    }

    @PostMapping("/ram/editar/{ramId}")
    public String editar(@PathVariable Integer ramId,
                         @ModelAttribute("formularioEditarRam") Ram ram,
                         @RequestPart("imagen") MultipartFile imagen) {
        try {
            Ram ramAnterior = ramService.obtenerIdRam(ramId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(ramAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en la RAM
                ram.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/ram/" + imagenNombre;

                // Establecer la URL de la nueva imagen en la RAM
                ram.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
                ram.setImagenNombre(ramAnterior.getImagenNombre());
                ram.setImagenArchivo(ramAnterior.getImagenArchivo());
            }

            // Actualizar la RAM en la base de datos
            ramService.actualizarRam(ram);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/ram";
    }

    @GetMapping("/ram/{ramId}")
    public String eliminar(@PathVariable Integer ramId) {
        Ram ram = ramService.obtenerIdRam(ramId);
        
        // Eliminar la imagen asociada a la RAM
        eliminarImagen(ram.getImagenNombre());
        
        // Eliminar la RAM de la base de datos
        ramService.eliminarRam(ramId);
        
        return "redirect:/admin/ram";
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
