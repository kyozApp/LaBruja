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
import com.labrujastore.entity.Placa;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.PlacaService;

@Controller
@RequestMapping("/admin")
public class PlacaController {

    private static String storageLocation = "src/main/resources/static/admin/placa";

    @Autowired
    private PlacaService placaService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/placa")
    public String index(Model model) {
        List<Placa> placas = placaService.listarPlaca();
        for (Placa placa : placas) {
            placa.setImagenArchivo("http://localhost:8080//admin/placa/" + placa.getImagenNombre());
        }
        model.addAttribute("tablaPlaca", placas);
        return "admin/placa/index";
    }

    @GetMapping("/placa/crear")
    public String crear(Model model) {
        Placa placa = new Placa();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearPlaca", placa);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/placa/crear";
    }

    @PostMapping("/placa/crear")
    public String crear(@ModelAttribute("formularioCrearPlaca") Placa placa,
                        @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en la placa
            placa.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/placa/" + imagenNombre;

            // Establecer la URL de la imagen en la placa
            placa.setImagenArchivo(imageUrl);

            // Guardar la placa en la base de datos
            placaService.guardarPlaca(placa);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/placa";
    }

    @GetMapping("/placa/editar/{placaId}")
    public String editar(@PathVariable Integer placaId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarPlaca", placaService.obtenerIdPlaca(placaId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/placa/editar";
    }

    @PostMapping("/placa/editar/{placaId}")
    public String editar(@PathVariable Integer placaId,
                         @ModelAttribute("formularioEditarPlaca") Placa placa,
                         @RequestPart("imagen") MultipartFile imagen) {
        try {
            Placa placaAnterior = placaService.obtenerIdPlaca(placaId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(placaAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en la placa
                placa.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/placa/" + imagenNombre;

                // Establecer la URL de la nueva imagen en la placa
                placa.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
                placa.setImagenNombre(placaAnterior.getImagenNombre());
                placa.setImagenArchivo(placaAnterior.getImagenArchivo());
            }

            // Actualizar la placa en la base de datos
            placaService.actualizarPlaca(placa);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/placa";
    }

    @GetMapping("/placa/{placaId}")
    public String eliminar(@PathVariable Integer placaId) {
        Placa placa = placaService.obtenerIdPlaca(placaId);
        
        // Eliminar la imagen asociada a la placa
        eliminarImagen(placa.getImagenNombre());
        
        // Eliminar la placa de la base de datos
        placaService.eliminarPlaca(placaId);
        
        return "redirect:/admin/placa";
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
