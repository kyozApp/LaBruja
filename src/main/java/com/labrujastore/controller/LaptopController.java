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
import com.labrujastore.entity.Laptop;
import com.labrujastore.service.CategoriaService;
import com.labrujastore.service.LaptopService;

@Controller
@RequestMapping("/admin")
public class LaptopController {

    private static String storageLocation = "src/main/resources/static/admin/laptop";

    @Autowired
    private LaptopService laptopService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/laptop")
    public String index(Model model) {
        List<Laptop> laptops = laptopService.listarLaptop();
        for (Laptop laptop : laptops) {
            laptop.setImagenArchivo("http://localhost:8080//admin/laptop/" + laptop.getImagenNombre());
        }
        model.addAttribute("tablaLaptop", laptops);
        return "admin/laptop/index";
    }

    @GetMapping("/laptop/crear")
    public String crear(Model model) {
        Laptop laptop = new Laptop();
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioCrearLaptop", laptop);
        model.addAttribute("selectorCategorias", categorias);
        return "admin/laptop/crear";
    }

    @PostMapping("/laptop/crear")
    public String crear(@ModelAttribute("formularioCrearLaptop") Laptop laptop,
                        @RequestPart("imagen") MultipartFile imagen) {
        try {
            // Generar un nombre único para el archivo de imagen
            String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

            // Establecer el nombre de la imagen en el laptop
            laptop.setImagenNombre(imagenNombre);

            // Obtener la ruta del archivo donde se almacenará la imagen
            Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

            // Copiar los bytes de la imagen al archivo en el sistema de archivos
            Files.copy(imagen.getInputStream(), filePath);

            // Construir la URL de la imagen
            String imageUrl = "http://localhost:8080//admin/laptop/" + imagenNombre;

            // Establecer la URL de la imagen en el laptop
            laptop.setImagenArchivo(imageUrl);

            // Guardar el laptop en la base de datos
            laptopService.guardarLaptop(laptop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/editar/{laptopId}")
    public String editar(@PathVariable Integer laptopId, Model model) {
        List<Categoria> categorias = categoriaService.listarCategoria();
        model.addAttribute("formularioEditarLaptop", laptopService.obtenerIdLaptop(laptopId));
        model.addAttribute("selectorCategorias", categorias);
        return "admin/laptop/editar";
    }

    @PostMapping("/laptop/editar/{laptopId}")
    public String editar(@PathVariable Integer laptopId,
                         @ModelAttribute("formularioEditarLaptop") Laptop laptop,
                         @RequestPart("imagen") MultipartFile imagen) {
        try {
            Laptop laptopAnterior = laptopService.obtenerIdLaptop(laptopId);

            // Verificar si se ha proporcionado una nueva imagen
            if (!imagen.isEmpty()) {
                // Eliminar la imagen anterior
                eliminarImagen(laptopAnterior.getImagenNombre());

                // Generar un nombre único para el archivo de imagen
                String imagenNombre = generarNombreUnico(imagen.getOriginalFilename());

                // Establecer el nombre de la nueva imagen en el laptop
                laptop.setImagenNombre(imagenNombre);

                // Obtener la ruta del archivo donde se almacenará la nueva imagen
                Path filePath = Paths.get(storageLocation + "/" + imagenNombre);

                // Copiar los bytes de la imagen al archivo en el sistema de archivos
                Files.copy(imagen.getInputStream(), filePath);

                // Construir la URL de la nueva imagen
                String imageUrl = "http://localhost:8080//admin/laptop/" + imagenNombre;

                // Establecer la URL de la nueva imagen en el laptop
                laptop.setImagenArchivo(imageUrl);
            } else {
                // Si no se proporciona una nueva imagen, mantener la imagen anterior
                laptop.setImagenNombre(laptopAnterior.getImagenNombre());
                laptop.setImagenArchivo(laptopAnterior.getImagenArchivo());
            }

            // Actualizar el laptop en la base de datos
            laptopService.actualizarLaptop(laptop);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/admin/laptop";
    }

    @GetMapping("/laptop/{laptopId}")
    public String eliminar(@PathVariable Integer laptopId) {
        Laptop laptop = laptopService.obtenerIdLaptop(laptopId);
        
        // Eliminar la imagen asociada a la laptop
        eliminarImagen(laptop.getImagenNombre());
        
        // Eliminar la laptop de la base de datos
        laptopService.eliminarLaptop(laptopId);
        
        return "redirect:/admin/laptop";
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
